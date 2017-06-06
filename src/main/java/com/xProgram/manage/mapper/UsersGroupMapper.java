package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xProgram.manage.model.UsersGroup;

public interface UsersGroupMapper {
	
	Integer getOpenGId(Map<String, Object> map);	
	
	Integer getOpenIdByGroupNexus(Map<String, Object> map);
	
	Integer getParentOpenIdByGroupNexus(Map<String, Object> map);
	
	Integer updateTotal(Map<String, Object> map);
	
	Integer upGroupCount(Map<String, Object> map);
	
	Integer insertGroup(Map<String, Object> map);
	
	Integer insertGroupNexus(UsersGroup usersGroup);
	
	List<UsersGroup> getAllGroup(Map<String, Object> map,@Param(value="campusId")Integer campusId,@Param(value="limit")Integer limit, @Param(value="offset")Integer offset, @Param(value="sort")String sort, @Param(value="order")String order,@Param(value="search")String search);
	
	List<UsersGroup> getAllUserGroup(Map<String, Object> map,@Param(value="campusId")Integer campusId,@Param(value="limit")Integer limit, @Param(value="offset")Integer offset, @Param(value="sort")String sort, @Param(value="order")String order,@Param(value="search")String search);
	
	Integer getGroupCount(Map<String, Object> map);
	
	Integer getUserGroupCount(Map<String, Object> map);


}
