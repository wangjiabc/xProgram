package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.FoodDetail;

public interface FoodDetailMapper {

	List<FoodDetail> getFoodDetailByKey(Integer foodId);
	
	int insertSelective(FoodDetail foodDetail);
	
	int upImgUrl(FoodDetail foodDetail);
	
	int insertId(Integer foodId);
	
	int insertUrl(Map<String, Object> paramMap);
	
	int deleteByPrimaryKey(Integer foodId);
	
}
