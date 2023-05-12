package com.zheng.spider.command;

import java.net.InetSocketAddress;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.cli.Command;

import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

public class SyncToCommand extends Command {

	static final Logger logger = LoggerFactory.getLogger(SyncToCommand.class);
	
	public SyncToCommand() { //StatefulRedisConnection<String, Object> conn
		 
	      super("sync", "run spider");
	      
	    //  connection=conn;
	}
	 
	@Override
    public void configure(Subparser subparser) {
		
        subparser.addArgument("-u", "--user")
                .dest("user")
                .type(String.class)
                .required(true)
                .help("run spider ");
    }
	
    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        System.out.println("sync " + namespace.getString("user"));
        
      
        
    }
   
}
