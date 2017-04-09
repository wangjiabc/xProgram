package com.xProgram.manage.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.manage.mapper.UsersMapper;
import com.xProgram.manage.model.SNSUserInfo;
import com.xProgram.manage.model.Users;
import com.xProgram.manage.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {
	private UsersMapper usersMapper;         //操作用户信息

	@Autowired
	public void setUsersMapper(UsersMapper usersMapper) {
		this.usersMapper = usersMapper;
	}

	public List<Users> getAllFullUser(Integer campusId,Integer limit, Integer offset, String sort,
			String order,String search) {
		return usersMapper.getAllFullUser(campusId,limit,offset,sort,order,search);
	}
	
	public Integer getUserCount(String campusAdmin ,Integer campusId,String search) {
		return usersMapper.getUserCount(campusAdmin,campusId,search);
	}

	public Integer getUserFullCount(Integer campusId,String search) {
		return usersMapper.getUserFullCount(campusId,search);
	}



	@Override
	public Integer getOpenId(Integer campusId, String openId) {
		// TODO Auto-generated method stub
		return usersMapper.getOpenId(campusId, openId);
	}

	@Override
	public Users getUserInfoById(Integer campusId, String openId) {
		// TODO Auto-generated method stub
		return usersMapper.getUserByOpenId(campusId, openId);
	}

	@Override
	public Integer insertUserInfo(SNSUserInfo snsUserInfo) {
		// TODO Auto-generated method stub
		return usersMapper.insertUserInfo(snsUserInfo);
	}

	@Override
	public Integer upUserByOpenId(SNSUserInfo snsUserInfo) {
		// TODO Auto-generated method stub
		return usersMapper.upUserByOpenId(snsUserInfo);
	}

	@Override
	public Integer insertInfoforUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersMapper.insertInfoforUser(map);
	}

	@Override
	public Integer upUserInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersMapper.upUserInfo(map);
	}

	@Override
	public List<Users> getAddress(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersMapper.getAddress(map);
	}

	@Override
	public Integer setDefaultAddress0(String openId) {
		// TODO Auto-generated method stub
		return usersMapper.setDefaultAddress0(openId);
	}

	@Override
	public Integer setDefaultAddress(Integer id) {
		// TODO Auto-generated method stub
		return usersMapper.setDefaultAddress(id);
	}

	@Override
	public Integer delAddress(Integer id) {
		// TODO Auto-generated method stub
		return usersMapper.delAddress(id);
	}

}
