package com.zheng.spider.htmlUnit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.htmlunit.BrowserVersion;
import org.htmlunit.FailingHttpStatusCodeException;
import org.htmlunit.ProxyConfig;
import org.htmlunit.SilentCssErrorHandler;
import org.htmlunit.WebClient;
import org.htmlunit.html.DomElement;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.DomNodeList;
import org.htmlunit.html.HtmlAnchor;
import org.htmlunit.html.HtmlButton;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlTable;
import org.htmlunit.html.HtmlTableCell;
import org.htmlunit.html.HtmlTextInput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zheng.spider.db.Db1Dao;
import com.zheng.spider.db.entity.SpiderFetchPage;
import com.zheng.spider.utils.IdWorker;


public class OkcisHtmlUnit {
	
	static final Logger logger = LoggerFactory.getLogger(OkcisHtmlUnit.class);

    // define usage of firefox, chrome or Edge
    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);
    
    static {
    	
    	   webClient.getCookieManager().setCookiesEnabled(true);
           webClient.getOptions().setJavaScriptEnabled(true);
           webClient.getOptions().setTimeout(20000);
           webClient.getOptions().setUseInsecureSSL(true);
           // overcome problems in JavaScript
           webClient.getOptions().setThrowExceptionOnScriptError(false);
           
           webClient.getOptions().setPrintContentOnFailingStatusCode(false);
           webClient.setCssErrorHandler(new SilentCssErrorHandler());
           
           /**使用代理
            * java -Dhttp.proxyHost=192.168.0.0 -Dhttp.proxyPort=4711 -jar myApp.ja
           final String proxyHost = System.getProperty("http.proxyHost");
           final String proxyPort = System.getProperty("http.proxyPort");
           ProxyConfig proxyConfig = new ProxyConfig(proxyHost, Integer.parseInt(proxyPort),null);
           webClient.getOptions().setProxyConfig(proxyConfig);
           ***/
           
           //试试加cookie
           
           try {

        	   /**
        	   //webClient.addCookie("cookieId=7XXF6TJ1683776810000",new URL("https://www.okcis.cn"),"www.okcis.cn");
               
        	   webClient.addCookie("PHPSESSID=gfnieni7hvo1dd0qvt2ie31ek6",new URL("https://www.okcis.cn"),".okcis.cn"); 
               //webClient.addCookie("jizhumimausernamenew=18607231007",new URL("https://www.okcis.cn"),".okcis.cn"); 
               
               //webClient.addCookie("HMF_CI=86e8a7573d5972cf0fd13bdb538e678a1ec70b092e3d590f2f8554fbec54f4386cc6354eafabc0416ca3762e53c754b845291019687575ce0925d1242dbf4f4a50",new URL("https://www.okcis.cn"),"www.okcis.cn"); 
               webClient.addCookie("sessionInfoKey=a13f7be34f9c1657017c67ac527ccd71",new URL("https://www.okcis.cn"),"www.okcis.cn"); 
               
               //
               webClient.addCookie("ZFY=yNGJ:BFy1SwOh0df4EFioDHHeU6Y7qn:BPPFhpwF4czoE:C",new URL("https://www.okcis.cn"),".baidu.com");
               webClient.addCookie("BIDUPSID=ADE1D14FEF212683EA9B8074DE60A13E",new URL("https://www.okcis.cn"),".baidu.com");
               webClient.addCookie("EEAkIjcBEarJg=15372079137",new URL("https://www.okcis.cn"),"www.okcis.cn");
               webClient.addCookie("et4321=2023-05-11+16%3A09%3A04COOKIEITEM15372079137COOKIEITEM117.150.92.2COOKIEITEM192.168.0.87",new URL("https://www.okcis.cn"),"www.okcis.cn");
               webClient.addCookie("PSTM=1675577103",new URL("https://www.okcis.cn"),".baidu.com");
               
               webClient.addCookie("HBB_HC=7b58b46c191328e2c15cf1ae23755755f5e1b141cad9523779df4fe29f0bfcd07374f0fd9bc8aa72e25bae86efe4fef1dd",new URL("https://www.okcis.cn"),"www.okcis.cn");
               webClient.addCookie("HMACCOUNT_BFESS=D3AE2D4792B8A336",new URL("https://www.okcis.cn"),".hm.baidu.com");
               
               ***/
           }catch(Exception ex) {
        	   logger.error("加cookie出错",ex);
           }

    }

    private IdWorker worker = new IdWorker(1, 1, 1);
    
    public boolean login() {
    	
    	try {
    		
    	   final HtmlPage page = webClient.getPage("https://www.okcis.cn/login/");
           
           //请输入关键字
           HtmlTextInput keywordTF = (HtmlTextInput) page.getElementById("k1");
           keywordTF.setText("毛发");
           
           //立即查询 search-button-search-on-new
           HtmlAnchor searchHA= page.getAnchorByName("search-button-search-on-new");
           
           searchHA.click();
           
    	}catch(Exception ex) {
    		
    	}
    	return false;
    }
    
    public   void seekTargePage(Db1Dao dao) {
        
        try {
        	
            final HtmlPage page = webClient.getPage("https://www.okcis.cn/search/");
            
            //请输入关键字
            HtmlTextInput keywordTF = (HtmlTextInput) page.getElementById("k1");
            keywordTF.setText("试剂");
            
            //立即查询 search-button-search-on-new
            HtmlAnchor searchHA= page.getAnchorByName("search-button-search-on-new");
            
            searchHA.click();
            
            webClient.waitForBackgroundJavaScript(15000);
            
            /***
            final HtmlTextInput searchField = htmlElementById(page,
                    "r-logo-keyword",
                    HtmlTextInput.class).get();
            
            // alternative to XPath: querySelector(".search-form__btn")
            final HtmlButton searchButton = htmlElementByXPath(page,
            		"//input[@name='result-logo-search-button' and @type='button']",
            		HtmlButton.class).get();
            
            searchField.setValueAttribute("毛发");

            final HtmlPage resultPage = searchButton.click();
            ***/
            
            boolean isBreak=false;
            do {
            	 
                try {
                
                	 HtmlPage resultPage=(HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
                     
                     //DomElement mainDiv =  resultPage.getElementById("pnamesss");
                     
                     //DomElement listDiv= mainDiv.querySelector(".tybl_list");
                     
                     DomElement listDiv= resultPage.getFirstByXPath("//*[@id='pnamesss']/div[1]");
                     
                     
                     List<DomNode> list= listDiv.querySelectorAll("a");
                     
                     //List<HtmlTableCell> list= htmlElementsByCssClass(resultPage, ".huag_tit_20160113", HtmlTableCell.class);
                     
                     // in real life you may use LOGGER.debug()
                     //  System.out.println("HTML source: " + resultPage.getWebResponse().getContentAsString());
                     list.stream().forEach(u->{
                     	
                     	HtmlAnchor link=(HtmlAnchor)u;
                     
                     	String href=link.getHrefAttribute().trim();
                     	String content=link.getTextContent().trim();
                     	
                     	logger.debug("列表 link:"+href+" content;"+content);
                     	
                     	if(dao.checkUrlExist("okcis",href)>0) {
                     	
                     		logger.debug( "url已存在，跳过");
                         	
                     	}else {
                     		
                     		SpiderFetchPage one =new SpiderFetchPage();

                         	one.setId(String.valueOf( worker.nextId()));
                         	one.setBussType("okcis");
                         	
                         	one.setTitle(content);
                         	one.setUrl(href);
                         	one.setFlag(0);
                         	one.setCreateDate(new Date());
                         	
                         	dao.insertFetchPage(one);
                     	}
                     	
                     });
                	
                	//下一页                    
                    HtmlAnchor nextPageHA= resultPage.getAnchorByText("下一页");
                    
                    nextPageHA.click();
                	
                    webClient.waitForBackgroundJavaScript(15000);
                    
                    isBreak=true;
                	
                }catch(Exception ex) {
                
                	logger.error("获取数据出错，跳出",ex);
                	
                	isBreak=false;
                }
            	
            }while(isBreak);
            
            
        } catch (Exception e) {
            
            logger.error("seekTargePage出现异常",e);
        }
    }
    
    
    public  Map<String,String>  getDetailPage(String name,String url) {
    	
    	Map<String,String> data=new HashMap<>();
    	
         try {
         	
             final HtmlPage page = webClient.getPage("https://www.okcis.cn"+url);
             
             webClient.waitForBackgroundJavaScript(5000);
             //内容详细
             DomElement contents=page.getElementById("contents");
             data.put("招标详情", contents.asNormalizedText());
             
             //HtmlTable table=  htmlElementByXPath(page,"/html/body/div[6]/div[5]/div[1]/ul[2]/table",HtmlTable.class).orElse(null);
             
             HtmlTable table= page.getFirstByXPath("//*[@id=\"tables\"]/table");
             
             //seek table item
            
             //招标编号
             DomElement text1= table.getFirstByXPath("//tbody/tr[1]/td[2]");
             data.put("招标编号", text1.asNormalizedText());
             
             //更新时间
             DomElement text2= table.getFirstByXPath("//tbody/tr[1]/td[4]");
             data.put("更新时间", text2.asNormalizedText());
             
             //所属地区
             DomElement text3= table.getFirstByXPath("//tbody/tr[2]/td[2]");
             data.put("所属地区", text3.asNormalizedText());
             
             //截止时间
             DomElement text4= table.getFirstByXPath("//tbody/tr[2]/td[4]");
             data.put("截止时间", text4.asNormalizedText());
             
             //招标联系人
             DomElement text5= table.getFirstByXPath("//tbody/tr[3]/td[2]");
             data.put("招标联系人", text5.asNormalizedText());
             
             //联系电话
             DomElement text6= table.getFirstByXPath("//tbody/tr[3]/td[4]");
             data.put("联系电话", text6.asNormalizedText());
             
             //所属行业
             DomElement text7= table.getFirstByXPath("//tbody/tr[4]/td[2]");
             data.put("所属行业", text7.asNormalizedText());
             
             //招标文件
             DomElement text8= table.getFirstByXPath("//tbody/tr[4]/td[4]");
             data.put("招标文件", text8.asNormalizedText());
             
             //招标业主单位
             DomElement text9= table.getFirstByXPath("//tbody/tr[4]/td[2]");
             data.put("招标业主单位", text9.asNormalizedText());
             
             //招标公司
             DomElement text10= table.getFirstByXPath("//tbody/tr[4]/td[4]");
             data.put("招标公司", text10.asNormalizedText());
             
             //中标候选
             DomElement text11= table.getFirstByXPath("//tbody/tr[6]/td[2]");
             data.put("中标候选", text11.asNormalizedText());
            
             //招标关键词
            // DomElement text12= table.getFirstByXPath("//tbody/tr[6]/td[2]");
             //data.put("招标关键词", text12.asNormalizedText());
             
         } catch (Exception e) {
             
             logger.error("getDetailPage 出现异常",e);
         }
         
         return data;
    	
    }

    public static <T> Optional<T> htmlElementByXPath(
           DomNode node,
           String xpath,
           Class<T> type) {
        return node.getByXPath(xpath).stream()
               .filter(o->type.isAssignableFrom(o.getClass()))
               .map(type::cast).findFirst();
    }

    public static <T> List<T> htmlElementsByCssClass(
    		DomNode node,
    		String cssClass,
    		Class<T> type) {
    	return node.querySelectorAll(cssClass).stream()
	        .filter(o->type.isAssignableFrom(o.getClass()))
	        .map(type::cast).collect(Collectors.toList());
    }

    public static <T> Optional<T> htmlElementById(
    		HtmlPage node,
    		String htmlTagId,
    		Class<T> type) {
    	return Optional.ofNullable(node.getElementById(htmlTagId)).map(type::cast);
    }
}