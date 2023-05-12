package com.zheng.spider.resources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zheng.spider.core.JsonBody;

@Path("/qrcode")
@Produces(MediaType.APPLICATION_JSON)
public class QrcodeResource {

	private static final Logger logger = LoggerFactory.getLogger(QrcodeResource.class);
	
	
    private HttpClient httpClient;
    
    private String openUrl;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    public QrcodeResource(HttpClient httpClient,String openUrl) {
    	
        this.httpClient=httpClient;
        this.openUrl=openUrl;
    }

    @GET
    public void receiveHello(String saying) {
    	logger.info("Received a saying: {}", saying);
    }
    
    
    /**
     * 1.5.1 两项基础数据可信身份认证接口（无人脸核验）
     * @param request
     * @param dto
     * @return
     */
    @SuppressWarnings("unchecked")
	@POST
    @Path("/open/verifyIdentity")
    public JsonBody<Map<String,Object>> verifyIdentity( @Context  HttpServletRequest request) {
    
    	Map<String,Object> data=new  HashMap<String,Object>();
    	
    	return new JsonBody<>(1,"success",data);
    }
  
}
