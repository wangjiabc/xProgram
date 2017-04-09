package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.Campus;
import com.xProgram.manage.model.CampusAdmin;

public interface CampusMapper {
    int deleteByPrimaryKey(Integer campusId);

    int insert(Campus record);

    int insertSelective(Campus record);

    Campus selectByPrimaryKey(Integer campusId);

    int updateByPrimaryKeySelective(Campus record);

    int updateByPrimaryKey(Campus record);

    List<Campus> selectAllCampus(Map<String, Object> paramMap);
    
    List<Campus> selectAllFullCampus(Map<String, Object> paramMap);

	Integer getIdByName(Map<String, Object> paramMap);
	
	Integer closeCampus(Map<String, Object> requestMap);
	
	Campus selectCampusById(Map<String, Object> paramMap);
	
	CampusAdmin getCampusIdByAdmin(Map<String, Object> paramMap);
	
	List<CampusAdmin> getAllCampusAdmin(Map<String, Object> paramMap);

	Integer updateCampusAdmin(Map<String, Object> paramMap);
	
	Integer addCampus(Map<String, Object> paramMap);

	Campus getCampusByOrder(Map<String, Object> paramMap);
	
	Integer deleteCampusAdmin(Map<String, Object> paramMap);
	
	Integer insertCampusAdmin(Map<String, Object> paramMap);
	
	Integer insertCity(Map<String, Object> paramMap);
	
	Integer updateCampus(Map<String, Object> paramMap);

	String getCampusName(Integer campusId);
}