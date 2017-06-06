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
		    
		    if(openId.equals(""))
		    	return null;
		    
		    SNSUserInfo snsUserInfo=null;
		    
		    JSONObject jsonObject;

		    jsonObject=getWxInfo(jsCode, iv, encryptedData);
		    
		    String openGId=jsonObject.getString("openGId");
		    
		    Map<String, Object> gidMap=new HashMap<>();
		    gidMap.put("openGId", openGId);
		    gidMap.put("currentTime", currentTime);
		    
		    Integer isOpenGId=usersGroupService.getOpenGId(gidMap);
		    if(isOpenGId.equals(0))
		     usersGroupService.insertGroup(gidMap);   //写入群id
			   			
			
			Map<String, Object> groupMap=new HashMap<>();
			groupMap.put("openId", openId);
			groupMap.put("openGId", openGId);
			groupMap.put("currentTime", currentTime);
		
			UsersGroup usersGroup=new UsersGroup();   
			usersGroup.setCurrentTime(currentTime);
		
		Integer isOpenIdGId=usersGroupService.getOpenIdByGroupNexus(groupMap);	
		
		if(isTransmit){			
		  if(isOpenIdGId.equals(0)){
			  usersGroup.setIsShare(0);
			  usersGroup.setOpenId(openId);
			  usersGroup.setOpenGId(openGId);
			  usersGroupService.insertGroupNexus(usersGroup); //写入转发
			  usersGroupService.upGroupCount(gidMap);         //增加群人数
			}else {
				usersGroup.setIsShare(1);
				usersGroupService.updateTotal(groupMap);     //增加转发数
			}
		 }else if(!isTransmit){
			 if(isOpenIdGId.equals(0)){
			   usersGroup.setIsShare(1);
			   usersGroup.setOpenId(openId);
			   usersGroup.setOpenGId(openGId);
			   usersGroupService.insertGroupNexus(usersGroup); //写入不转发
			   usersGroupService.upGroupCount(gidMap);         //增加群人数
			 }
		 }else {
			Map<String, Object> add=new HashMap<>();
			add.put("openId", openId);
			add.put("openGId", openGId);
			add.put("currentTime", currentTime);
			usersGroupService.updateTotal(add);
		  }	   
			
		    
	    
		    return jsonObject.toString();
	}
	
	@RequestMapping("/test")
	public @ResponseBody Integer
	testController(HttpServletRequest request, HttpServletResponse response
			){
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
		usersGroup.setCurrentTime(currentTime);
		
		Integer isOpenIdGId=usersGroupService.upGroupCount(gidMap); 
		
		
	    return isOpenIdGId;
	}
	
	@RequestMapping("/getAllUserGroup")
	public @ResponseBody Map<String, Object>
	getAllUserGroup(HttpServletRequest request, HttpServletResponse response,
			Integer limit,Integer offset,String sort,String order,
			String search)
			throws ServletException, IOException{
		   int campusId=1;
		   Map<String,Object> map=new HashMap<>();
		   
		   List<UsersGroup> usersGroups;
		   
		   /*
			 * 前端的user表与其它表不一样，必须指定查询参数，否则抛出sql异常
			 * 默认按id降序排列
			 */
			if(sort==null){
				sort="id";
				order="desc";
			}
			
			Map<String, Object> map2=new HashMap<>();
 			
			if(search!=null&&!search.trim().equals("")){
				search="%"+search+"%";
				map2.put("search", search);
			}		

		   
		   usersGroups=usersGroupService.getAllUserGroup(map,campusId,limit,offset,sort,order,search);
		   
		   Map<String, Object> resultMap=new HashMap<>();
		   
		   
		   resultMap.put("rows", usersGroups);

		   resultMap.put("total", usersGroupService.getUserGroupCount(map2));
		   
		   return resultMap;
		
	}
	
	
	@RequestMapping("/getAllGroup")
	public @ResponseBody Map<String, Object>
	getAllGroup(HttpServletRequest request, HttpServletResponse response,
			Integer limit,Integer offset,String sort,String order,
			String search)
			throws ServletException, IOException{
		int campusId=1;
		   Map<String,Object> map=new HashMap<>();
		   
		   List<UsersGroup> usersGroups;
		
		   /*
			 * 前端的user表与其它表不一样，必须指定查询参数，否则抛出sql异常
			 * 默认按id降序排列
			 */
			if(sort==null){
				sort="id";
				order="desc";
			}
			
			Map<String, Object> map2=new HashMap<>();
			
			if(search!=null&&!search.trim().equals("")){
				search="%"+search+"%";
				map2.put("search", search);
			}		
			
		   
          usersGroups=usersGroupService.getAllGroup(map,campusId,limit,offset,sort,order,search);
		   
		   Map<String, Object> resultMap=new HashMap<>();
		   
		   
		   resultMap.put("rows", usersGroups);
		   resultMap.put("total", usersGroupService.getGroupCount(map2));
		   		   
		   return resultMap;
	}
	
}
