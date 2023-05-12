package com.zheng.spider;

import com.codahale.metrics.annotation.Timed;
import com.zheng.spider.command.CallSpiderCommand;
import com.zheng.spider.command.SyncToCommand;
import com.zheng.spider.config.ApplicationConfiguration;
import com.zheng.spider.core.ManagedJobsTask;
import com.zheng.spider.core.Template;
import com.zheng.spider.db.Db1Dao;
import com.zheng.spider.filter.DateRequiredFeature;
import com.zheng.spider.health.DatabaseHealthCheck;
import com.zheng.spider.health.TemplateHealthCheck;
import com.zheng.spider.jobs.FetchSiteUrlJob;
import com.zheng.spider.jobs.ProcessSiteUrlJob;
import com.zheng.spider.tasks.EchoTask;

import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.*;

public class ExamApp extends Application<ExamConfig> {
    
	private  final Logger logger = LoggerFactory.getLogger(ExamApp.class);
	
    public static void main(String[] args) throws Exception {
       
    	//server config/config.yml
    	//call-spider -b okcis
        new ExamApp().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<ExamConfig> bootstrap) {

    	bootstrap.addCommand(new SyncToCommand());
    	bootstrap.addCommand(new CallSpiderCommand());
        
    }

    @Override
    public String getName() {
        return "ABCExam";
    }
    
    @Override
    public void run(ExamConfig configuration, Environment environment) throws InvalidKeySpecException, NoSuchAlgorithmException, InterruptedException, ExecutionException {
    	
    	logger.debug("初始化" );
		
    	ApplicationConfiguration appCfg=configuration.getAppCfg();
    	
		//template
        final Template template = configuration.buildTemplate();

        final JdbiFactory factory = new JdbiFactory();
        
        final Jdbi db1Jdbi = factory.build(environment, configuration.getDataSource1Factory(), "db1");
        
        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        
        environment.healthChecks().register("database", new DatabaseHealthCheck(db1Jdbi));
        
        final Db1Dao db1Dao = db1Jdbi.onDemand(Db1Dao.class);
        
        environment.admin().addTask(new EchoTask());
        
        //启动job
        FetchSiteUrlJob  fetchSiteUrlJob =new FetchSiteUrlJob(db1Dao);
        final Managed managedFetchSiteUrlJob = new ManagedJobsTask(fetchSiteUrlJob);
       // environment.lifecycle().manage(managedFetchSiteUrlJob);    
        
        ProcessSiteUrlJob  processSiteUrlJob =new ProcessSiteUrlJob(db1Dao);
        final Managed managedProcessSiteUrlJob = new ManagedJobsTask(processSiteUrlJob);
        environment.lifecycle().manage(managedProcessSiteUrlJob);    
       
        environment.jersey().register(DateRequiredFeature.class);
   	    	 	
    	FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORSFilter", CrossOriginFilter.class);
    	
    	filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, environment.getApplicationContext().getContextPath() + "*");
        
        
        environment.jersey().register(new RestResource());
    }

    @Path("/")
    @Timed
    public static class RestResource {
    	
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/hello")
        public Object get() {            
        	
            return "fuck the world";
        }
    }

}
