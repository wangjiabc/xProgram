package com.xProgram.manage.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.manage.mapper.UsersGroupMapper;
import com.xProgram.manage.model.UsersGroup;
import com.xProgram.manage.service.UsersGroupService;

@Service("userGroupService")
public class UsersGroupServiceImpl implements UsersGroupService{
	private UsersGroupMapper usersGroupMapper;
	
	@Autowired
	public void setUsersGroupMapper(UsersGroupMapper usersGroupMapper) {
		this.usersGroupMapper = usersGroupMapper;
	}

	




	@Override
	public Integer insertGroupNexus(UsersGroup usersGroup) {
		// TODO Auto-generated method stub
		return usersGroupMapper.insertGroupNexus(usersGroup);
	}



	@Override
	public Integer insertGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersGroupMapper.insertGroup(map);
	}



	@Override
	public Integer getOpenGId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersGroupMapper.getOpenGId(map);
	}


	@Override
	public Integer getOpenIdByGroupNexus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersGroupMapper.getOpenIdByGroupNexus(map);
	}


	@Override
	public Integer getParentOpenIdByGroupNexus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersGroupMapper.getParentOpenIdByGroupNexus(map);
	}

	@Override
	public Integer updateTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersGroupMapper.updateTotal(map);
	}



	@Override
	public List<UsersGroup> getAllGroup(Map<String, Object> map,Integer campusId,Integer limit, Integer offset, String sort,
			String order,String search) {
		// TODO Auto-generated method stub
		return usersGroupMapper.getAllGroup(map,campusId,limit,offset,sort,order,search);
	}


	@Override
	public List<UsersGroup> getAllUserGroup(Map<String, Object> map,Integer campusId,Integer limit, Integer offset, String sort,
			String order,String search) {
		// TODO Auto-generated method stub
		return usersGroupMapper.getAllUserGroup(map,campusId,limit,offset,sort,order,search);
	}



	@Override
	public Integer getGroupCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersGroupMapper.getGroupCount(map);
	}



	@Override
	public Integer getUserGroupCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersGroupMapper.getUserGroupCount(map);
	}

	

}
