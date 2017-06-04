package com.xProgram.inswept.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xProgram.manage.controller.testController;
import com.xProgram.manage.model.SNSUserInfo;
import com.xProgram.manage.model.UsersGroup;
import com.xProgram.manage.model.WxInfo;
import com.xProgram.manage.service.UsersGroupService;
import com.xProgram.manage.service.WxInfoService;
import com.xProgram.manage.tools.AdvancedUtil;
import com.xProgram.manage.tools.WXBizDataCrypt;

@Controller
@RequestMapping("/insweptUserGroup")
public class userGroupController {

	
	private WxInfoService wxInfoService;
	
	private UsersGroupService usersGroupService;
	
	
	@Autowired
	public void setWxInfoService(WxInfoService wxInfoService) {
		this.wxInfoService = wxInfoService;
	}
	
	@Autowired
	public void setUsersGroupService(UsersGroupService usersGroupService) {
		this.usersGroupService = usersGroupService;
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
			@RequestParam String encryptedData,
			@RequestParam String openId,
			@RequestParam boolean isTransmit)
			throws ServletException, IOException {
		    int campusId=1;
		    Date currentTime=new Date();
		    
		    SNSUserInfo snsUserInfo=null;
		    
		    JSONObject jsonObject;

		    jsonObject=getWxInfo(jsCode, iv, encryptedData);
		    
		    String openGId=jsonObject.getString("openGId");
		    
		    Map<String, Object> gidMap=new HashMap<>();
		    gidMap.put("openGId", openGId);
		    
		    Integer isOpenGId=usersGroupService.getOpenGId(gidMap);
		    if(isOpenGId.equals(0))
		     insertGroup(openGId);   //–¥»Î»∫id
			   			
			
			Map<String, Object> groupMap=new HashMap<>();
			groupMap.put("openId", openId);
			groupMap.put("openGId", openGId);
			
			Integer isOpenIdGId=usersGroupService.getOpenIdByGroupNexus(groupMap);
			
			UsersGroup usersGroup=new UsersGroup();   
			usersGroup.setOpenId(openId);
			usersGroup.setFinallyTime(currentTime);
			
		if(isOpenIdGId.equals(0)){
			if(isTransmit){
			  usersGroup.setIsShare(0);
			  usersGroup.setOpenGId(openGId);
			}else {
				usersGroup.setIsShare(1);
				usersGroup.setParentOpenGid(openGId);
			}
		 }else {
			Map<String, Object> add=new HashMap<>();
			add.put("openId", openId);
			add.put("openGId", openGId);
			add.put("currentTime", currentTime);
			usersGroupService.updateTotal(add);
		  }	   
			
		    insertGroupNexus(usersGroup);
	    
		    return jsonObject.toString();
	}
	
	@RequestMapping("/test")
	public @ResponseBody String
	testController(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> gidMap=new HashMap<>();
	    gidMap.put("openGId", "aaa");
	    
	    Integer isOpenGId=usersGroupService.getOpenGId(gidMap);
	    
	    Map<String, Object> groupMap=new HashMap<>();
		String openId="yyyy";
		String openGId="oooo";
		
		Map<String, Object> add=new HashMap<>();
		add.put("openId", openId);
		add.put("openGId", openGId);
		Date currentTime=new Date();
		add.put("currentTime", currentTime);
		
		UsersGroup usersGroup=new UsersGroup();   
		usersGroup.setOpenId(openId);
		usersGroup.setParentOpenGid(openGId);
		usersGroup.setFinallyTime(currentTime);
		
		Integer isOpenIdGId=insertGroupNexus(usersGroup);
		
	    return isOpenIdGId.toString();
	}
	
	@RequestMapping("/getAllUserGroup")
	public @ResponseBody List<UsersGroup>
	getAllUserGroup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		   int campusId=1;
		   Map<String,Object> map=new HashMap<>();
		   
		   List<UsersGroup> usersGroups;
		   
		   map.put("campusId", campusId);
		   
		   usersGroups=usersGroupService.getAllUserGroup(map);
		   		 
		   return usersGroups;
		
	}
	
	private Integer 
	insertGroup(String openGId){
		Map<String, Object> map=new HashMap<>();
		
		map.put("openGId", openGId);
		
		int i=usersGroupService.insertGroup(map);
		
		return i;
		
	}
	
	private Integer
	insertGroupNexus(UsersGroup usersGroup){
		int i=usersGroupService.insertGroupNexus(usersGroup);
		
		return i;
	}
}
