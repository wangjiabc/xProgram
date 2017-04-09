package com.xProgram.manage.serviceImpl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.manage.mapper.FoodCategoryMapper;
import com.xProgram.manage.mapper.FoodDetailMapper;
import com.xProgram.manage.mapper.FoodMapper;
import com.xProgram.manage.model.Food;
import com.xProgram.manage.model.FoodCategory;
import com.xProgram.manage.model.FoodDetail;
import com.xProgram.manage.service.FoodService;

/**
 * 食品管理服务类（包括食品分类）
 * @author 殿下
 *2014/12/13
 */
@Service("/foodService")
public class FoodServiceImpl implements FoodService{
	private static final Logger logger = LoggerFactory.getLogger("FoodServiceImpl");
	
    private FoodMapper foodMapper;
    private FoodCategoryMapper foodCategoryMapper;
    private FoodDetailMapper foodDetailMapper;
    
    @Autowired
    public void setFoodDetailMapper(FoodDetailMapper foodDetailMapper) {
		this.foodDetailMapper = foodDetailMapper;
	}
 
    public FoodCategoryMapper getFoodDetailMapper(){
    	return foodCategoryMapper;
    }
    
    @Autowired
	public void setFoodCategoryMapper(FoodCategoryMapper foodCategoryMapper) {
		this.foodCategoryMapper = foodCategoryMapper;
	}
    
	public FoodCategoryMapper getFoodCategoryMapper() {
		return foodCategoryMapper;
	}

	public FoodMapper getFoodMapper() {
		return foodMapper;
	}

    @Autowired
	public void setFoodMapper(FoodMapper foodMapper) {
		this.foodMapper = foodMapper;
	}

	public int deleteFoodByPrimaryKey(Map<String,Object> paramMap) {
		       Integer foodId=(Integer) paramMap.get("foodId");
		       foodDetailMapper.deleteByPrimaryKey(foodId); //删除商品详情foodid
		return foodMapper.deleteByPrimaryKey(paramMap);
	}

	public int insertFoodSelective(Food record) {

		int flag=foodMapper.insertSelective(record); //写入新商品
		
	    Integer foodId=foodMapper.getLastInsertId();  //Mysql 中获取刚插入的自增长id的值
        foodDetailMapper.insertId(foodId); //写入商品详情foodid
	    
		return flag;
	}

	public Food selectFoodByPrimaryKey(Integer foodId) {
		return foodMapper.selectByPrimaryKey(foodId);
	}

	public int updateFoodByPrimaryKeySelective(Food record) {
		return foodMapper.updateByPrimaryKeySelective(record);
	}



	public List<Food> getAllFoods(Map<String,Object> paramMap) {
		return foodMapper.getAllFoods(paramMap);
	}


	public Integer changeFoodCount(Map<String,Object> paramMap) {
		int flag=0;
		//flag= foodSpecialMapper.changeFoodCount(paramMap);
		flag=foodMapper.changeFoodNumber(paramMap);
		return flag;		
	}


	@Override
	public Integer uploadHomeFoodByFoodId(Map<String, Object> paramMap) {
		return foodMapper.uploadHomeFoodByFoodId(paramMap);
	}

	@Override
	public Integer updateInfoByFoodId(Map<String, Object> paramMap) {
		return foodMapper.updateInfoByFoodId(paramMap);
	}

	@Override
	public Integer cancelRecommend(Map<String, Object> paramMap) {
		return foodMapper.cancelRecommend(paramMap);
	}

	@Override
	public List<FoodCategory> getAllFoodCategories() {
		// TODO Auto-generated method stub
		return foodCategoryMapper.getAllFoodCategories();
	}

	@Override
	public int updateFoodStatusByPrimaryKey(Integer foodId) {
		// TODO Auto-generated method stub
		return foodMapper.updateStatusBySelective(foodId);
	}

	@Override
	public Integer addFoodCountById(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return foodMapper.addFoodCountById(paramMap);
	}

	@Override
	public Food getAllFoodByPrimaryKey(Integer foodId) {
		// TODO Auto-generated method stub
		return foodMapper.getAllByPrimaryKey(foodId);
	}

	@Override
	public List<FoodDetail> getDetailByKey(Integer foodId) {
		// TODO Auto-generated method stub
		return foodDetailMapper.getFoodDetailByKey(foodId);
	}

	@Override
	public int insertFoodDetailSelect(FoodDetail foodDetail) {
		// TODO Auto-generated method stub
		       if(!foodDetail.getImgUrl().equals(""))
		        foodDetailMapper.upImgUrl(foodDetail);   //更新商品图片
		return foodDetailMapper.insertSelective(foodDetail);
	}

	@Override
	public int insertFoodUrl(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return foodDetailMapper.insertUrl(paramMap);
	}

	@Override
	public int getFoodCountById(Integer foodId) {
		// TODO Auto-generated method stub
		return foodMapper.getFoodCount(foodId);
	}

	@Override
	public int selectStatusByKey(Integer foodId) {
		// TODO Auto-generated method stub
		return foodMapper.selectStatusByKey(foodId);
	}

	@Override
	public int updateStart(Map<String, Object> renMap) {
		// TODO Auto-generated method stub
		return foodMapper.updateStart(renMap);
	}

	@Override
	public int updateEnd(Map<String, Object> renMap) {
		// TODO Auto-generated method stub
		return foodMapper.updateEnd(renMap);
	}

	@Override
	public int selectStart(Integer foodId) {
		// TODO Auto-generated method stub
		return foodMapper.selectStart(foodId);
	}

	
}

