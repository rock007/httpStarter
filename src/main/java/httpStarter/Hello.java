package httpStarter;

import java.util.HashMap;
import java.util.Map;

public class Hello {

	public static void main(String[] args) {
		
		String y = HttpTookit.doGet("http://my.51job.com/my/My_SignIn.php", null, "GBK", true); 
		
		//51job.com Ê§°Ü
		Map<String, String> params=new HashMap<String,String>();
		/****
		params.put("username", "wenhua1225@hotmail.com");
		params.put("userpwd", "spring1999");
		
		y=HttpTookit.doPost("https://mylogin.51job.com/17656537023567550030/my/My_Pmc.php", params, "GB2312", true);
		****/
		
		params=new HashMap<String,String>();
		params.put("loginname", "wenhua1225@hotmail.com");
		params.put("password", "spring1999");
		
		y=HttpTookit.doPost("http://my.zhaopin.com/loginmgr/loginproc.asp", params, "UTF-8", true);
		
		
        System.out.println(y); 
        
	}

}
