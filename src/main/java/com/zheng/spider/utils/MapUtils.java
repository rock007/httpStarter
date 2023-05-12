/**
* sam@here 2019年10月15日
**/
package com.zheng.spider.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MapUtils {


    public static Map <String, Object> objectToMap(Object obj){
    	
    	Map <String, Object> map = new HashMap <String, Object>();
        
    	if(obj == null)
    		
            return map;

        try {
        	BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor property : propertyDescriptors){
                String key = property.getName();
                if(key.compareToIgnoreCase("class") == 0){
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ?getter.invoke(obj) : null;
                map.put(key, value);
            }
        }catch(Exception ex) {
        	
        }
        return map;
    }

    public static <T> T mapToObject(Map <String, Object> map,Class<T> requiredType){
    	
    	try {
    		
        	Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        	
        	return gson.fromJson(gson.toJsonTree(map), requiredType);
        	
    	}catch(Exception ex) {
    		
    		ex.printStackTrace();
    	}
    	
        return null;
    }

   

    /**
     * 获取val
     */
    public static String getMapVal(Map <String, Object> paramMap, String key){
        if(paramMap == null || key == null){
            return "";
        }
        return paramMap.get(key) == null ?"" : paramMap.get(key).toString();
    }

    public static String getMapValByString(Map <String, String> paramMap, String key){
        if(paramMap == null || key == null){
            return "";
        }
        return paramMap.get(key) == null ?"" : paramMap.get(key).toString();
    }

    /**
     * 实体类转map
     */
    public static Map<String, Object> ConvertObjToMap(Object obj, String... ignoreProperties){
       
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

    	Map <String, Object> reMap = new HashMap <String, Object>();
        if(obj == null)
            return null;

        try{
        	BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor property : propertyDescriptors){
                String key = property.getName();
                if(key.compareToIgnoreCase("class") == 0){
                    continue;
                }
                //加过滤属性
                if(ignoreList != null && ignoreList.contains(key)){
                	
                	continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ?getter.invoke(obj) : null;
                reMap.put(key, value);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return reMap;
    }
    
    public static Map<String,Object> fromString(String body){
    	
    	Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		Map<String,Object> data = gson.fromJson(body,new TypeToken<Map<String,Object>>() {}.getType()); 
		
		return data;
    }

}


