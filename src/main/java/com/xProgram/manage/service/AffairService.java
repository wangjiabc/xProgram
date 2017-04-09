package com.xProgram.manage.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.xProgram.manage.model.Affair;

public interface AffairService {


	int insertTest(Map<String, Object> paramMap);
	
	int insertTest2(Map<String, Object> paramMap);
	
	int insertTest3(Map<String, Object> paramMap);
	
	int insertAll(Map<String, Object> paramMap);
	
	int insert1(Map<String, Object> paramMap);
	
	int insert2(Map<String, Object> paramMap);
	
	Affair selectTest3();
	
}
