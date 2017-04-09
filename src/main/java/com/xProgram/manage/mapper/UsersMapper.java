package com.xProgram.manage.mapper;

import java.awt.Image;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xProgram.manage.model.SNSUserInfo;
import com.xProgram.manage.model.Users;
import com.xProgram.manage.model.WxInfo;

public interface UsersMapper {
    Integer deleteByPrimaryKey(String phone);

    Integer insert(Users record);

    Integer insertSelective(Users record);    

    Integer updateByPrimaryKeySelective(Users record);

    Integer updateByPrimaryKey(Users record);
    
    Integer insertUserInfo(SNSUserInfo snsUserInfo);
    
    Integer upUserByOpenId(SNSUserInfo snsUserInfo);
    
    //**新增方法

    Integer insertInfoforUser(Map<String,Object> map);
    
    Integer upUserInfo(Map<String, Object> map);
    
    List<Users> getAddress(Map<String, Object> map);
    
    Integer setDefaultAddress0(String openId);
    
    Integer setDefaultAddress(Integer id);
    
    Integer delAddress(Integer id);
    
	List<Users> getAllFullUser(@Param(value="campusId")Integer campusId,@Param(value="limit")Integer limit, @Param(value="offset")Integer offset, @Param(value="sort")String sort, @Param(value="order")String order,@Param(value="search")String search);

	Integer getOpenId(@Param(value="campusId")Integer campusId,@Param(value="openId")String openId);
	
	Users getUserByOpenId(@Param(value="campusId")Integer campusId,@Param(value="openId")String openId);
	
	Integer getUserCount(@Param(value="campusAdmin")String campusAdmin,@Param(value="campusId")Integer campusId,@Param(value="search")String search);
	
	Integer getUserFullCount(@Param(value="campusId")Integer campusId,@Param(value="search")String search);

	Integer setUserAdmin(@Param(value="phone")String phone, @Param(value="campusId")Integer campusId);

	Integer setUserCommon(@Param(value="phone")String phone,@Param(value="campusId")Integer campusId);

	Integer setUserSuperAdmin(@Param(value="phone")String phone, @Param(value="campusId")Integer campusId);

	int updateUserImage(@Param(value="imageUrl")String imageUrl, @Param(value="phone")String phone);

	String getImageUrl(@Param(value="phone")String phone);

	List<Users> getDeliverAdmin(Map<String, Object> paramMap);

	int setUserToken(@Param(value="phone")String phoneId, @Param(value="token")String token);

	String getUserToken(@Param(value="togetherId")String togetherId);

	int clearOldToken(@Param(value="token")String token);

	String getUserPhone(@Param(value="togetherId")String togetherId);

	List<String> getAllSuperAdminPhone(Map<String, Object> paramterMap);      //获取所有的超级管理员

	String getUserTokenByPhone(Map<String, Object> paramterMap);      //获取用户token

	Integer getCountsByDevice(Map<String, Object> paramMap);   //获取不同设备用户的个数

	List<Users> selectByPhoneAndPassword(Map<String, Object> paramMap);
	
	Users checkLogin(String phone);

	List<String> getUserByType(Map<String, Object> paramMap);
}