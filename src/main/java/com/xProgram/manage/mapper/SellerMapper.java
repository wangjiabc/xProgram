package com.xProgram.manage.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xProgram.manage.model.Sellers;

public interface SellerMapper {


	Sellers selectByCampusAdmin(String campusAdmin);

	void updateLastLoginTime(@Param(value="date")Date date, @Param(value="campusAdmin") String campusAdmin
			);

	int insertSellective(Sellers seller);

	Sellers selectByCampusId(String campusAdmin);

	List<Sellers> getCampusAdmin(String campusAdmin);

	List<Sellers> getAllCampusAdmin(); 
	
	int selectMaxCityId();
	
	int selectRepeatAdmin(@Param(value="campusAdmin") String campusAdmin);

}
