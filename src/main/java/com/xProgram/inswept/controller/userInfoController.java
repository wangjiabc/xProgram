package com.xProgram.inswept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xProgram.manage.model.SNSUserInfo;
import com.xProgram.manage.model.Users;
import com.xProgram.manage.model.WxInfo;
import com.xProgram.manage.service.UserService;
import com.xProgram.manage.service.WxInfoService;
import com.xProgram.manage.tools.AdvancedUtil;
import com.xProgram.manage.tools.WXBizDataCrypt;

@Controller
@RequestMapping("/insweptUser")
public class userInfoController {
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
	
	
	private static SNSUserInfo getSNSUserInfo(JSONObject jsonObject){
        SNSUserInfo snsUserInfo=new SNSUserInfo();
        if (jsonObject!=null) {
        	try{
        		if(jsonObject.getString("errcode")!=null){
	        		String errcode=jsonObject.getString("errcode");
	        		System.out.println("errcoid="+errcode);
                    snsUserInfo.setErrorCode(true);       //����΢�Ŵ������룬�Ա�getUserInfo������������access_token
                    return snsUserInfo;
        	  }
        	 }catch (Exception e) {
				// TODO: handle exception
			}
            try {
                // �û��ı�ʶ
                snsUserInfo.setOpenId(jsonObject.getString("openId"));
                // �ǳ�
                snsUserInfo.setNickname(jsonObject.getString("nickName"));
                // �Ա���1�����ԣ�2��Ů�ԣ�0��δ֪��
                snsUserInfo.setSex(Short.parseShort(jsonObject.getString("gender")));
                //�û�������
                snsUserInfo.setLanguage(jsonObject.getString("language"));
                // �û����ڹ���
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // �û�����ʡ��
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // �û����ڳ���
                snsUserInfo.setCity(jsonObject.getString("city"));
                // �û�ͷ��
                snsUserInfo.setHeadImgUrl(jsonObject.getString("avatarUrl"));
                //ֻ�����û������ںŰ󶨵�΢�ſ���ƽ̨�ʺź󣬲Ż����ָ��ֶΡ�
                snsUserInfo.setUnionid(jsonObject.getString("unionId"));
            } catch (Exception e) {
                snsUserInfo = null;
                String errorCode = jsonObject.getString("errcode");
                String errorMsg = jsonObject.getString("errmsg");
               e.printStackTrace();
            }
        }else{
        	System.out.println("json=null");
        }
        return snsUserInfo;
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
	
	private void insertUser(SNSUserInfo snsUserInfo,Integer campusId, String openId) {
		//д�����û�
        //�ж��Ƿ�����openid

        Integer isOpenId=userService.getOpenId(campusId, openId);          
        snsUserInfo.setCampusId(campusId); 
		
        //�����ھͲ���������
        if(isOpenId.equals(0)){       	  
       	 userService.insertUserInfo(snsUserInfo);
         }
        else{      	  
           Users userinfo=userService.getUserInfoById(campusId, openId);                    
          //�ж������Ƿ���ԭ���������ȣ���������д�����ݿ�
           if(!userinfo.getNickname().equals(snsUserInfo.getNickname())||
       		  !userinfo.getHeadImgUrl().equals(snsUserInfo.getHeadImgUrl())||!userinfo.getLanguage().equals(snsUserInfo.getLanguage())||
       		   !userinfo.getSex().equals(snsUserInfo.getSex())){
           	 userService.upUserByOpenId(snsUserInfo);
       	     System.out.println("NOT equals!!!!");
       	   }else {
				System.out.println("OK !!!");
			}         
        }

	}
	
	@RequestMapping("/getUserInfo")
	public @ResponseBody String 
	getUserInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String jsCode,@RequestParam String iv,
			@RequestParam String encryptedData)
			throws ServletException, IOException {
		    int campusId=1;

		    SNSUserInfo snsUserInfo=null;
		    
		    JSONObject jsonObject;

		    jsonObject=getWxInfo(jsCode, iv, encryptedData);
		    
		    List list=new ArrayList<Object>();
		    JSONArray jsonArray=new JSONArray();
		    
			snsUserInfo=getSNSUserInfo(jsonObject);

			String openId=snsUserInfo.getOpenId();
			
			insertUser(snsUserInfo, campusId, openId);
			
			list.add(jsonObject);
			jsonArray.add(list);
			
	        return jsonArray.toString();
		
	}
	
	
	@RequestMapping("/getKey")
	public @ResponseBody String 
	getkey(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String jsCode){
		 int campusId=1;

		    SNSUserInfo snsUserInfo=new SNSUserInfo();
		    
		    JSONObject jsonObject;

		    String appId,appSecret;
	
			WxInfo wxInfo=wxInfoService.getAppId(1);
			
			String deString=null;
			
			appId=wxInfo.getAppId();
		    appSecret=wxInfo.getAppSecret();
		    
		    try {
				JSONObject aString=AdvancedUtil.getAccessToken(appId, appSecret, jsCode);
		    	
		    
		        jsonObject = aString;
		        System.out.println("jsonobject="+jsonObject);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String a=org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
				jsonObject=JSONObject.parseObject("{\"errcode\":\""+a+"\"}");
				e.printStackTrace();
				deString="error";
			}  
		    
	        return jsonObject.toString();
	}
	
}
