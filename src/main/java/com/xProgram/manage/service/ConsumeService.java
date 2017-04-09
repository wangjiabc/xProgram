package com.xProgram.manage.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xProgram.manage.model.Consume;

public interface ConsumeService {

	Consume selectConsumeByOpenId(Integer campusId,String campusAdmin,String openId);
	
	Consume selectConsumeInfoById(Integer id);
	
	int selectConsumeUsedById(Integer id);
	
	List<Consume> selectAllConsumeByOpenId(Integer campusId,String openId);

	int upOrderUseType(Integer id);
	
	int upOrderUsedById(Integer id,Integer campusId,String openId);
	 
}
