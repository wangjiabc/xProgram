package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xProgram.manage.model.Purchase;

public interface PurchaseMapper {

	Integer decreaseById(@Param(value="amount")Integer amount,@Param(value="foodId")Integer foodId);
		
	Integer insertIntoOrders(Map<String, Object> paramMap);
	
	List<Purchase> selectBuyInfoByOpenId(@Param(value="campusId")Integer campusId,@Param(value="openId")String openId);
	
}
