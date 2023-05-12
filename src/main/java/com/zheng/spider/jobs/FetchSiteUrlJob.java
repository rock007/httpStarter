package com.zheng.spider.jobs;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.zheng.spider.db.Db1Dao;
import com.zheng.spider.htmlUnit.OkcisHtmlUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
 
//获取要抓取的页面job
public class FetchSiteUrlJob extends AbstractScheduledService {
 
    private final Logger logger = LoggerFactory.getLogger(FetchSiteUrlJob.class);
 
    private Db1Dao dao;
    
    private static boolean isRunning=false;
    
    private OkcisHtmlUnit okcisHtmlUnit=new OkcisHtmlUnit();
    
    public FetchSiteUrlJob(Db1Dao _dao) {
    	
    	this.dao=_dao;
    }
    
    @Override
	protected void runOneIteration() throws Exception {

    	try {
    		
    		logger.debug("获取要抓取的页面");
    		
    		if(isRunning) {
    			
    			logger.debug("获取要抓取的页面job任务正在执行,这次取消");
    			return;
    		}
    		
    		isRunning=true;
    		
    		okcisHtmlUnit.seekTargePage(dao);
    		
    	}catch (Exception ex) {
    		
    		logger.error("执行抓取目标页面定时作务出错:", ex);
    		
    	}finally {
    		
    		isRunning=false;
    		logger.debug("获取要抓取的页面任务已结束");
    	} 

	}
 
    @Override
    protected AbstractScheduledService.Scheduler scheduler() {
        return AbstractScheduledService.Scheduler.newFixedRateSchedule(0, 15, TimeUnit.MINUTES);
    }
}