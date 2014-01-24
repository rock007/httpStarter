package httpStarter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class TestZhaoPin {

    private static Log log = LogFactory.getLog(TestZhaoPin.class); 
    
	public static void main(String[] args) {
		
		//获取登陆页	    
	    final WebClient webClient = new WebClient();
        HtmlPage loginPage;
		try {
			
			loginPage = webClient.getPage("http://my.zhaopin.com/loginmgr/lognerro.asp");
			
	        System.out.println(loginPage.getTitleText());
	        System.out.println("loginPage>content:"+loginPage.getTextContent());
	        
	        // Adding listeners
	        webClient.addWebWindowListener(new com.gargoylesoftware.htmlunit.WebWindowListener() {

	            public void webWindowOpened(WebWindowEvent event) {
	                
	                System.out.println(" opened WebWindow: " + event.getNewPage().getUrl());
	            }

	            public void webWindowContentChanged(WebWindowEvent event) {
	            	
	            	Page newPage=event.getNewPage();
	            	/***
	            	if(newPage.getUrl().toString().endsWith("f=login")){
	            		
	            	 System.out.println("main:"+newPage.getWebResponse().getContentAsString());
	            		
	            		HtmlPage myMainPage=new HtmlPage(newPage.getUrl(),newPage.getWebResponse(),newPage.getEnclosingWindow());
	            		
	            		System.out.println("myMainPage:"+myMainPage.asText());
	            	   
	            	}
	            	***/
	            	
	            	
	            	System.out.println("change:"+event.getNewPage().getUrl());
	            }

	            public void webWindowClosed(WebWindowEvent event) {
	                
	                System.out.println("closed WebWindow: " + event.getOldPage().getUrl());
	            }
	        });
	        
			//登陆
	        HtmlForm loginForm = loginPage.getFormByName("frmLogin");

	        HtmlTextInput usernameTF = loginForm.getInputByName("loginname");
	        HtmlPasswordInput userpwdTF = loginForm.getInputByName("password");
	        
	        usernameTF.setValueAttribute("wenhua1225@hotmail.com");
	        userpwdTF.setValueAttribute("spring1999");
	        
	        HtmlSubmitInput submitButton = loginForm.getInputByName("loginbutton");
	        
	        //http://my.zhaopin.com/loginmgr/loginproc.asp
	        HtmlPage loginAfterPage = submitButton.click();

	        webClient.waitForBackgroundJavaScript(15000);
	        
	        System.out.println("loginAfterPage:"+loginAfterPage.asText());
	        
	        //http://my.zhaopin.com/MYZHAOPIN/resume_preview.asp?ext_id=JR238363014R90250000000&resume_id=133409267&Version_Number=1&language_id=1&t=.1012646&fromtype=popdiv

	        //HtmlPage resumePage= webClient.getPage("http://my.zhaopin.com/MYZHAOPIN/resume_preview.asp?ext_id=JR238363014R90250000000&resume_id=133409267&Version_Number=1&language_id=1&t=.1012646&fromtype=popdiv");
	        //webClient.waitForBackgroundJavaScript(10000);
	        
	        HtmlPage myPage= (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
	        
	        System.out.println("title:"+myPage.getTitleText());
	        System.out.println("content:"+myPage.getTextContent());
	        
	        DomElement slides_1= myPage.getElementById("slides_1");
	        
	        DomNodeList<HtmlElement> nodeList=  slides_1.getElementsByTagName("a");
	       List<?> linkList= myPage.getByXPath( "//a[@class='funBtn ic6']");
	       
	       System.out.println("linkList.size:"+linkList.size());
	       
	       if(linkList.size()>=2){
	    	   
	    	   webClient.getOptions().setJavaScriptEnabled(false);
	    	   
	    	   HtmlAnchor linkE1=(HtmlAnchor)linkList.get(1);
	    	   
	    	   WebResponse rep= linkE1.click().getWebResponse();
	    	   
	    	   
	    	   //webClient.setAjaxController(new NicelyResynchronizingAjaxController());
	    	   
	    	   //HtmlPage resumePage = linkE1.click().getWebResponse();
	    	   //webClient.waitForBackgroundJavaScript(15000);
	    	   
	    	   System.out.println("my resume:"+rep.getContentAsString());
	    	   
	       }
	       
			
		} catch (FailingHttpStatusCodeException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

		
		//获取简历

	}

}
