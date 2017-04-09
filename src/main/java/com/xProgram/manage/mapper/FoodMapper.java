package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;


import com.xProgram.manage.model.Food;

public interface FoodMapper {
	//查询商品审核状态
	int selectStatusByKey(Integer foodId);
	
	//查询商品是否开始
	int selectStart(Integer foodId);
	
	//更新商品开始状态
	int updateStart(Map<String, Object> renMap);
	
	//更新商品结束状态
	int updateEnd(Map<String, Object> renMap);
	
    int deleteByPrimaryKey(Map<String, Object> paramMap);

    int insert(Food record);

    Integer getLastInsertId();
    
    int insertSelective(Food record);

    Food selectByPrimaryKey(Map<String, Object> paramMap);

    int updateByPrimaryKeySelective(Integer foodId);

    int updateByPrimaryKey(Food record);

	List<Food> getAllFoods(Map<String, Object> paramMap);        //web管理端获取所有的食品

	int changeFoodNumber(Map<String, Object> paramMap);   //更新销量和减少库存


	Integer uploadHomeFoodByFoodId(Map<String, Object> paramMap);//上传推送到首页的食品大图
	
	Integer updateInfoByFoodId(Map<String, Object> paramMap);//更新食品详情图片
	
	Integer cancelRecommend(Map<String, Object> paramMap);//取消推荐
	
	Integer addFoodCountById(Map<String, Object> paramMap);//添加库存
	

	String getFoodHomeImage(Map<String, Object> paramMap);

	String getDetailImg(Map<String, Object> paramMap);   //获取食品详情图片

	Food selectByPrimaryKey(Integer foodId);
	
    Food getAllByPrimaryKey(Integer foodId);

	int updateByPrimaryKeySelective(Food record);
	
	int updateStatusBySelective(Integer foodId);
	
	int getFoodCount(Integer foodId);
}