package com.zheng.spider.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import io.dropwizard.servlets.tasks.PostBodyTask;

public class EchoTask extends PostBodyTask {
	
    public EchoTask() {
        super("echo");
    }

    @Override
    public void execute(Map<String, List<String>> parameters, String body, PrintWriter output) throws Exception {
        output.print(body);
        output.flush();
    }
    
}