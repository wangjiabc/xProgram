package com.xProgram.manage.tools;

import com.alibaba.fastjson.JSONObject;

public class AdvancedUtil {
	
	/*������������ͨ���ص���code��ȡһ����������ҳ��Ȩaccess_token
	 * ��ȷʱ���ص�JSON���ݰ����£�
	 * { "access_token":"ACCESS_TOKEN",    
	     "expires_in":7200,    
	     "refresh_token":"REFRESH_TOKEN",    
	     "openid":"OPENID",    
	     "scope":"SCOPE" } 
	 */
	 public static JSONObject getAccessToken(String appId, String appSecret,String js_code) {
	        JSONObject wat = null;
	        // ƴ��������ַ
	        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	        requestUrl = requestUrl.replace("APPID", appId);
	        requestUrl = requestUrl.replace("SECRET", appSecret);
	        requestUrl = requestUrl.replaceAll("JSCODE", js_code);
	        // ��ȡ��ҳ��Ȩƾ֤
	        
	        System.out.println("requesUrl="+requestUrl);
	        
	        
	    //    JSONObject jsonObject = Curl.conn(requestUrl);
	        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
	        
	        System.out.println("jsonObject="+jsonObject);
	        
	        	try{
		        	  if(jsonObject.getString("errcode")!=null){
		        		String errcode=jsonObject.getString("errcode");
		        		System.out.println("errcoid="+errcode);
		        	  }
		        	 }catch (Exception e) {
						// TODO: handle exception
		        		 e.printStackTrace();
		        		 String a=org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
			             wat = JSONObject.parseObject("{\"errcode exception\":\""+a+"\"}");
					     return wat;
		        	 }
	        	
	        	
	            try {
	                wat = jsonObject;
	            } catch (Exception e) {
	            	String a=org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
	                wat = JSONObject.parseObject("{\"json exception\":\""+a+"\"}");
	                String errorCode = jsonObject.getString("errcode");
	                String errorMsg = jsonObject.getString("errmsg");
	               e.printStackTrace();
	               return wat;
	            }
	     
	        return wat;
	    }
}
