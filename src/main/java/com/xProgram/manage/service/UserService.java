package com.xProgram.manage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xProgram.manage.model.SNSUserInfo;
import com.xProgram.manage.model.Users;
import com.xProgram.manage.model.WxInfo;


public interface UserService {

	
	List<Users> getAllFullUser(Integer campusId,Integer limit, Integer offset, String sort,String order,String search);
	
	Users getUserInfoById(Integer campusId,String openId);
	
	Integer getUserCount(String campusAdmin,Integer campusId,String search);
	
	Integer getUserFullCount(Integer campusId,String search);
	
	Integer getOpenId(Integer campusId,String openId);
	
	Integer insertUserInfo(SNSUserInfo snsUserInfo);
	    
	Integer upUserByOpenId(SNSUserInfo snsUserInfo);
	
	List<Users> getAddress(Map<String, Object> map);
	
	Integer insertInfoforUser(Map<String,Object> map);
	
	Integer upUserInfo(Map<String, Object> map);
	
    Integer setDefaultAddress0(String openId);
    
    Integer setDefaultAddress(Integer id);
    
    Integer delAddress(Integer id);

}