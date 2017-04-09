package com.xProgram.manage.serviceImpl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.manage.mapper.CampusMapper;
import com.xProgram.manage.model.Campus;
import com.xProgram.manage.model.CampusAdmin;
import com.xProgram.manage.service.CampusService;

@Service("campusService")
public class CampusServiceImpl implements CampusService {
	private CampusMapper campusMapper;

	@Autowired
	public void setCampusMapper(CampusMapper campusMapper) {
		this.campusMapper = campusMapper;
	}

	public List<Campus> getAllCampus(Map<String, Object> paramMap) {
		return campusMapper.selectAllCampus(paramMap);
	}

	public List<Campus> getAllFullCampus(Map<String, Object> paramMap) {
		return campusMapper.selectAllFullCampus(paramMap);
	}
	
	@Override
	public Integer getIdByName(Map<String, Object> paramMap) {
		return campusMapper.getIdByName(paramMap);
	}


	@Override
	public Campus getCampusById(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return campusMapper.selectCampusById(paramMap);
	}
	
	@Override
	public CampusAdmin getCampusIdByAdmin(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return campusMapper.getCampusIdByAdmin(paramMap);
	}

	@Override
	public List<CampusAdmin> getAllCampusAdmin(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return campusMapper.getAllCampusAdmin(paramMap);
	}

	@Override

	public Integer updateCampusAdmin(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return campusMapper.updateCampusAdmin(paramMap);
	}


	public Campus getCampus(Map<String, Object> paramMap) {
		return campusMapper.getCampusByOrder(paramMap);
	}

	@Override
	public Integer deleteCampusAdmin(Map<String, Object> paramMap) {
		return campusMapper.deleteCampusAdmin(paramMap);
	}

	@Override
	public Integer addCampusAdmin(Map<String, Object> paramMap) {
		return campusMapper.insertCampusAdmin(paramMap);
	}


	@Override
	public Integer updateCampus(Map<String, Object> paramMap) {
		return campusMapper.updateCampus(paramMap);
	}

	@Override
	public String getCampusName(Integer campusId) {
		return campusMapper.getCampusName(campusId);
	}



}
