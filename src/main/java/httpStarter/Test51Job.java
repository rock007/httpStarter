package httpStarter;


import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.MalformedURLException;  
import java.util.List;  

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;  
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;  
import com.gargoylesoftware.htmlunit.ScriptResult;  
import com.gargoylesoftware.htmlunit.WebClient;  
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;  
import com.gargoylesoftware.htmlunit.html.HtmlPage;  
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;  
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class Test51Job {

    private static Log log = LogFactory.getLog(Test51Job.class); 
    
	public static void main(String[] args) {
		
		//获取登陆页	    
	    final WebClient webClient = new WebClient();
        HtmlPage loginPage;
		try {
			
			loginPage = webClient.getPage("http://my.51job.com/my/My_SignIn.php");
	        System.out.println(loginPage.getTitleText());
			
			//登陆
	        HtmlForm loginForm = loginPage.getFormByName("signin");

	        HtmlTextInput usernameTF = loginForm.getInputByName("username");
	        HtmlPasswordInput userpwdTF = loginForm.getInputByName("userpwd");
	        
	        usernameTF.setValueAttribute("wenhua1225@hotmail.com");
	        userpwdTF.setValueAttribute("spring1999");
	        
	        HtmlButton submitButton = (HtmlButton)loginPage.createElement("button");
	        submitButton.setAttribute("type", "submit");
	        loginForm.appendChild(submitButton);
	        
	        webClient.getOptions().setThrowExceptionOnScriptError(false);
	        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
	        webClient.getOptions().setThrowExceptionOnScriptError(false);
	        webClient.getOptions().setUseInsecureSSL(true);
	        
	        HtmlPage loginAfterPage = submitButton.click();
	        
	        //执行按钮出发的js事件
	        /***
	        ScriptResult sr = loginPage.executeJavaScript("javascript:chkSignIn('CN', 'https://mylogin.51job.com');");  
	        
	        HtmlPage loginAfterPage = (HtmlPage) sr.getNewPage(); 
	        ***/
	        //登陆后首页
	        System.out.print("title:"+loginAfterPage.getTitleText());
	       // System.out.print("content:"+loginAfterPage.asText());
	        //获取简历列表
	        List<?> nodeList=  loginAfterPage.getByXPath("/html/body/div/div[3]/div[2]");
	        System.out.println(nodeList);
	        
		    List<?> linkList= loginAfterPage.getByXPath( "/html/body/div/div[3]/div[2]/ul/li[3]/a");
		    System.out.println(linkList);
		    
		    if(linkList.size()>=1){
		    	   
		    	   webClient.getOptions().setJavaScriptEnabled(false);
		    	   
		    	   HtmlAnchor linkE1=(HtmlAnchor)linkList.get(0);
		    	   
		    	   HtmlPage resumeManagerPage=  linkE1.click();
		    	   
		    	   DomElement  div_recon_wbg= resumeManagerPage.getElementById("recon_wbg");
		    	   List<?> resumelinkList=div_recon_wbg.getByXPath("//table/tbody//tr[2]/td[9]/a");
		    	   
		    	   System.out.println(resumelinkList);
		    	   
		    	   if(resumelinkList.size()>=1){
		    		   
		    		   HtmlAnchor resumeE1=(HtmlAnchor)resumelinkList.get(0);
		    		
		    		   WebResponse rep= resumeE1.click().getWebResponse();
			    	   
			    	   System.out.println("resume :"+rep.getContentAsString());
		    	   }
		    	   
		    	   
		    	   
		       }
	        
	        
		} catch (FailingHttpStatusCodeException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
				
	}

}
