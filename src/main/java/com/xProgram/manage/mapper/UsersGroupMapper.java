package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.UsersGroup;

public interface UsersGroupMapper {
	
	Integer insertGroup(Map<String, Object> map);
	
	Integer insertGroupNexus(UsersGroup usersGroup);
	
	List<UsersGroup> getAllUserGroup(Map<String, Object> map);


}
