package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xProgram.manage.model.Users;
import com.xProgram.manage.model.UsersGroup;

public interface UsersGroupMapper {
	
	Integer insertGroup(Map<String, Object> map);
	
	Integer insertGroupNexus(UsersGroup usersGroup);
	
	List<UsersGroup> getAllUserGroup(Map<String, Object> map);


}
