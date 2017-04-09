package com.xProgram.manage.service;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.Items;
import com.xProgram.manage.model.Order;

public interface CartService {

	List<Items> getFoodDetailById(Map<String, Object> renMap);
	
	Integer insertIntoOrders(Map<String, Object> map);
	
	Integer delToOrders(Map<String, Object> map);
	
	Integer delALLOrders(Map<String, Object> map);
	
	List<Order> getAllOrder(Map<String, Object> map);
}
