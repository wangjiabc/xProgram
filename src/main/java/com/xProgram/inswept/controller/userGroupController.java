package com.xProgram.inswept.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xProgram.manage.model.SNSUserInfo;
import com.xProgram.manage.model.WxInfo;
import com.xProgram.manage.service.UserService;
import com.xProgram.manage.service.WxInfoService;
import com.xProgram.manage.tools.AdvancedUtil;
import com.xProgram.manage.tools.WXBizDataCrypt;

@Controller
@RequestMapping("/insweptUserGroup")
public class userGroupController {

private UserService userService;
	
	private WxInfoService wxInfoService;
	
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setWxInfoService(WxInfoService wxInfoService) {
		this.wxInfoService = wxInfoService;
	}
	
	private JSONObject getWxInfo(String jsCode,String iv,String encryptedData) {
		WxInfo wxInfo;
		
		String appId,appSecret;
		
		JSONObject jsonObject;
		
		wxInfo=wxInfoService.getAppId(1);
		
		String deString=null;
		
		appId=wxInfo.getAppId();
	    appSecret=wxInfo.getAppSecret();
	    
	    
				try {
					JSONObject aString=AdvancedUtil.getAccessToken(appId, appSecret, jsCode);
			    	
			    	if(aString!=null)
			    	System.out.println("session_key="+aString.getString("session_key"));
			    	
			        String sessionKey = aString.getString("session_key");
			        
			        if(sessionKey==null){
			        	return aString;
			        }
			        				        	
					deString = WXBizDataCrypt.getInstance().decrypt(encryptedData, sessionKey, iv, "utf-8");
					System.out.println("destring="+deString);
					
					if(deString==null){
						return aString;
					}
					
			        jsonObject = JSONObject.parseObject(deString);  
			        System.out.println("jsonobject="+jsonObject);  
				} catch (Exception e) {
					// TODO Auto-generated catch block
					String a=org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
					jsonObject=JSONObject.parseObject("{\"errcode\":\""+a+"\"}");
					e.printStackTrace();
					deString="error";
				}  
	    return jsonObject;
	}
	
	@RequestMapping("/getGroupInfo")
	public @ResponseBody String 
	getGroupInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String jsCode,@RequestParam String iv,
			@RequestParam String encryptedData,@RequestParam boolean isTransmit)
			throws ServletException, IOException {
		    int campusId=1;

		    SNSUserInfo snsUserInfo=null;
		    
		    JSONObject jsonObject;

		    jsonObject=getWxInfo(jsCode, iv, encryptedData);
		    
		    String openId=userInfoController.getOpenId();
		    
		    String openGId=jsonObject.getString("openGId");
		    
		    return jsonObject.toString()+"  openid="+openId;
	}
	
	
}
