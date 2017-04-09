package com.xProgram.manage.service;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.FoodCategory;
import com.xProgram.manage.model.FoodDetail;
import com.xProgram.manage.model.Food;

public interface FoodService {

	int insertFoodSelective(Food record);
	
	int insertFoodDetailSelect(FoodDetail foodDetail);

	int insertFoodUrl(Map<String, Object> paramMap);
	
	Food selectFoodByPrimaryKey(Integer foodId);
	
	Food getAllFoodByPrimaryKey(Integer foodId);

	int updateFoodByPrimaryKeySelective(Food record);

	int updateFoodStatusByPrimaryKey(Integer foodId);
	
	Integer addFoodCountById(Map<String, Object> paramMap);  //添加库存

	List<Food> getAllFoods(Map<String, Object> paramMap);

	int selectStatusByKey(Integer foodId);
	
	int selectStart(Integer foodId);
	
    int updateStart(Map<String, Object> renMap);
	
	int updateEnd(Map<String, Object> renMap);

	int deleteFoodByPrimaryKey(Map<String, Object> paramMap);


	Integer changeFoodCount(Map<String, Object> paramMap);


	
	Integer uploadHomeFoodByFoodId(Map<String, Object> paramMap);
	
	Integer updateInfoByFoodId(Map<String, Object> paramMap);
	
	Integer cancelRecommend(Map<String, Object> paramMap);


	List<FoodCategory> getAllFoodCategories();
	
	List<FoodDetail> getDetailByKey(Integer foodId);
	
	int getFoodCountById(Integer foodId);
	
}
