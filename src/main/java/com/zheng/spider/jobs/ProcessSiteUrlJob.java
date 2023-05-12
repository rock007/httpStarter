package com.zheng.spider.jobs;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.zheng.spider.db.Db1Dao;
import com.zheng.spider.db.entity.SpiderFetchPage;
import com.zheng.spider.htmlUnit.OkcisHtmlUnit;
import com.zheng.spider.utils.DateUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
 
//处理抓取的页面
public class ProcessSiteUrlJob extends AbstractScheduledService {
 
    private static final String CON_SETTING_BUSS_LOG_KEY ="last_fetch_create_date";

	private final Logger logger = LoggerFactory.getLogger(ProcessSiteUrlJob.class);
 
    private Db1Dao dao;
    
    private static boolean isRunning=false;
    
    private OkcisHtmlUnit okcisHtmlUnit=new OkcisHtmlUnit();
    
    public ProcessSiteUrlJob(Db1Dao _dao) {
    	
    	this.dao=_dao;
    }
    
    @Override
	protected void runOneIteration() throws Exception {
    	
    	try {
    		
    		logger.debug("开始执行抓取目标页面");
    		
    		if(isRunning) {
    			
    			logger.debug("获取要抓取的页面job任务正在执行,这次取消");
    			return;
    		}
    		
    		isRunning=true;
    		
    		String lastDateStr=dao.getSettingByKey(CON_SETTING_BUSS_LOG_KEY);
    		
    		if(StringUtils.isEmpty(lastDateStr)) {
    			
    			lastDateStr=DateUtil.fullDateStr(new Date());
    		}
    		
    		logger.debug("上次同步时间"+lastDateStr);
    		
    		List<SpiderFetchPage> list=  dao.getFetchPageList(lastDateStr,1000);
    		
    		logger.debug("获取"+list.size()+"记录");
    		
    		for(SpiderFetchPage one:list) {
    			
    			
    			Map<String,String> data= okcisHtmlUnit.getDetailPage(one.getTitle(), one.getUrl());
    			
    			if(data==null||data.size()<=10) {
    				
    				logger.warn("页面信息返回不对，请检查"+data.toString());
    				
    				for (Map.Entry<String, String> entry : data.entrySet()) {
    			        String mapKey = entry.getKey();
    			        String mapValue = entry.getValue();
    			        
    			        logger.warn(one.getUrl()+ " 名称"+mapKey+"  url"+mapValue);
    				}
    				
    				one.setFlag(-1);
        			
        			dao.updateFetchPage(one);
        			
    				continue;
    			}
    			
    			//招标编号
    			one.setKey1Name("招标编号");
    			one.setKey1Text(data.getOrDefault("招标编号", ""));
    			//更新时间
    			one.setKey2Name("更新时间");
    			one.setKey2Text(data.getOrDefault("更新时间", ""));
    			
    			//所属地区
    			one.setKey3Name("所属地区");
    			one.setKey3Text(data.getOrDefault("所属地区", ""));
    			
    			//截止时间
    			one.setKey4Name("截止时间");
    			one.setKey4Text(data.getOrDefault("截止时间", ""));
    			
    			//招标联系人
    			one.setKey5Name("招标联系人");
    			one.setKey5Text(data.getOrDefault("招标联系人", ""));
    			
    			//联系电话
    			one.setKey6Name("联系电话");
    			one.setKey6Text(data.getOrDefault("联系电话", ""));
    			
    			//所属行业
    			one.setKey7Name("所属行业");
    			one.setKey7Text(data.getOrDefault("所属行业", ""));
    			
    			//招标文件
    			one.setKey8Name("招标文件");
    			one.setKey8Text(data.getOrDefault("招标文件", ""));
    			
    			//招标业主单位
    			one.setKey9Name("招标业主单位");
    			one.setKey9Text(data.getOrDefault("招标业主单位", ""));
    			
    			//招标公司
    			one.setKey10Name("招标公司");
    			one.setKey10Text(data.getOrDefault("招标公司", ""));
    			
    			//中标候选
    			one.setKey11Name("中标候选");
    			one.setKey11Text(data.getOrDefault("中标候选", ""));
    			
    			//招标关键词
    			one.setKey12Name("招标关键词");
    			one.setKey12Text(data.getOrDefault("招标关键词", ""));
    			
    			//招标详情
    			one.setKey13Name("招标详情");
    			one.setKey13Text(data.getOrDefault("招标详情", ""));
    			
    			one.setFlag(1);
    			
    			dao.updateFetchPage(one);
    			
    			logger.debug("修改sysLog记录同步状态 id:"+one.getId());
    		}
    		
    		if(list.size()>1) {
    			
    			dao.updateSetting(CON_SETTING_BUSS_LOG_KEY, DateUtil.fullDateStr(list.get(list.size()-1).getCreateDate()));	
    		}
    		
    	}catch (Exception ex) {
    		
    		logger.error("执行抓取目标页面定时作务出错:", ex);
    	}finally {
    		
    		isRunning=false;
    		logger.debug("执行抓取目标页面已完成");
    	} 

	}
 
    @Override
    protected AbstractScheduledService.Scheduler scheduler() {
        return AbstractScheduledService.Scheduler.newFixedRateSchedule(0, 5, TimeUnit.MINUTES);
    }
}