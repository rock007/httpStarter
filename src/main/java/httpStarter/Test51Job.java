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
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
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
	        
	        HtmlPage loginAfterPage = submitButton.click();
	        
	        //执行按钮出发的js事件
	        /***
	        ScriptResult sr = loginPage.executeJavaScript("javascript:chkSignIn('CN', 'https://mylogin.51job.com');");  
	        
	        HtmlPage loginAfterPage = (HtmlPage) sr.getNewPage(); 
	        ***/
	        
	        log.debug(loginAfterPage.getTitleText());
	        log.debug(loginAfterPage.getTextContent());
			
		} catch (FailingHttpStatusCodeException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

		
		//获取简历
		
		
	}

}
