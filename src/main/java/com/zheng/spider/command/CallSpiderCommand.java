package com.zheng.spider.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zheng.spider.magi.pipeline.OneFilePipeline;
import com.zheng.spider.magic.CraftDemo;
import com.zheng.spider.magic.MamacnPageProcessor;
import com.zheng.spider.magic.SinaBlogProcessor;

import io.dropwizard.cli.Command;

import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class CallSpiderCommand extends Command {

	static final Logger logger = LoggerFactory.getLogger(CallSpiderCommand.class);
	
	public CallSpiderCommand() { //StatefulRedisConnection<String, Object> conn
		 
	      super("call-spider", "run spider");
	      
	    //  connection=conn;
	}
	 
	@Override
    public void configure(Subparser subparser) {
		
        subparser.addArgument("-b", "-buss")
                .dest("buss")
                .type(String.class)
                .required(true)
                .help("run spider ");
    }
	
    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        String buss=namespace.getString("buss");
        
        logger.debug("spider buss is" + namespace.getString("buss"));
        
        if(buss.equals("okcis")) {
        	 Spider.create(new CraftDemo())
        	 .setScheduler(new FileCacheQueueScheduler("/data/webmagic/"+buss))
        	 .addUrl("https://github.com/rock0715").thread(5)
        	 .addPipeline(new ConsolePipeline())
        	 .addPipeline(new OneFilePipeline("/data/webmagic/"+buss+"/data"))
        	 .run();
        }
        
    }
   
}
