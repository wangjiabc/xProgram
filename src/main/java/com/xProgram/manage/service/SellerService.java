package com.xProgram.manage.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xProgram.manage.model.Sellers;


public interface SellerService {

	public Sellers selectByCampusAdmin(String campusAdmin);
	
	public Sellers selectByCampusId(String campusAdmin);

	public void updateLastLoginTime(Date date, String campusAdmin);

	public void addASeller(Sellers seller);
	
	public List<Sellers> getCampusAdmin(String campusAdmin);
	
	public List<Sellers> getAllCampusAdmin();
	
	public int selectMaxCityId();
	
	public int selectRepeatAdmin(String campusAdmin);
}
