package com.xProgram.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xProgram.manage.model.Users;
import com.xProgram.manage.model.UsersGroup;

public interface UsersGroupMapper {
	
	Integer insert(UsersGroup usersGroup);
	
	List<UsersGroup> getAllUserGroup(@Param(value="limit")Integer limit, @Param(value="offset")Integer offset);


}
