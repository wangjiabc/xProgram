package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.Items;

public interface CartMapper {

	List<Items> getFoodDetailById(Map<String, Object> renMap);
	
	Integer insertIntoOrders(Map<String, Object> map);
	
	Integer delToOrders(Map<String, Object> map);
	
	Integer delALLOrders(Map<String, Object> map);
	
}
