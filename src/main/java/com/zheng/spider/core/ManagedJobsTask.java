package com.zheng.spider.core;


import com.google.common.util.concurrent.AbstractScheduledService;
import io.dropwizard.lifecycle.Managed;
 
public class ManagedJobsTask implements Managed {
    
    private final AbstractScheduledService periodicTask;
 
    public ManagedJobsTask(AbstractScheduledService periodicTask) {
        this.periodicTask = periodicTask;
    }
 
    @Override
    public void start() throws Exception {
        periodicTask.startAsync().awaitRunning();
    }
 
   @Override
   public void stop() throws Exception {
       periodicTask.stopAsync().awaitTerminated();
   }
}