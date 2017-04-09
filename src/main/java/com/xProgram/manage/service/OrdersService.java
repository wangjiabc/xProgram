package com.xProgram.manage.service;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.Order;

public interface OrdersService {
	
	List<Order>	selectAllOrdersByCampusId(Map<String, Object> paramMap);
	
}
