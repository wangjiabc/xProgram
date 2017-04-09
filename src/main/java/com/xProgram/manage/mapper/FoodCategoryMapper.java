package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;


import com.xProgram.manage.model.FoodCategory;

public interface FoodCategoryMapper {
    int deleteByPrimaryKey(Map<String, Object> paramMap);

    int insert(FoodCategory record);

    int insertSelective(FoodCategory record);

    FoodCategory selectByPrimaryKey(Map<String, Object> paramMap);

    int updateByPrimaryKeySelective(FoodCategory record);

    int updateByPrimaryKey(FoodCategory record);

	List<FoodCategory> getFirstCategory(Map<String, Object> paramMap);

	//List<FoodCategory> getSecondCategoryes(@Param(value="categoryId")Integer id);

	List<FoodCategory> getAllFoodSecondCategories();

	List<FoodCategory> getAllFoodFirstCategories();

	List<FoodCategory> getAllFoodCategories();
	
	int getAllCategoryCount();
	
	Integer addCategoryWhenAddCampus(Map<String, Object> paramMap);
}