package com.xProgram.manage.service;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.Campus;
import com.xProgram.manage.model.CampusAdmin;

public interface CampusService {

	List<Campus> getAllCampus(Map<String, Object> paramMap);
	
	List<Campus> getAllFullCampus(Map<String, Object> paramMap);

	Integer getIdByName(Map<String, Object> paramMap);
	
	public Campus getCampusById(Map<String, Object> paramMap);
	
	public CampusAdmin getCampusIdByAdmin(Map<String, Object> paramMap);
	
	public List<CampusAdmin> getAllCampusAdmin(Map<String, Object> paramMap);
	
	public Integer updateCampusAdmin(Map<String, Object> paramMap);

	Campus getCampus(Map<String, Object> paramMap);      //从订单信息里面获取校区信息
	
	Integer deleteCampusAdmin(Map<String,Object> paramMap);		//删除某校区的某管理员
	
	Integer addCampusAdmin(Map<String, Object> paramMap);	//添加校区管理员
		
	Integer updateCampus(Map<String, Object> paramMap);

	String getCampusName(Integer campusId);          //根据校区id获取校区名称
}
