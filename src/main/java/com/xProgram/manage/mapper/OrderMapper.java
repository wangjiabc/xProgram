package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.Order;

public interface OrderMapper {

   List<Order>	selectAllOrdersByCampusId(Map<String, Object> paramMap);
	
   List<Order> getAllOrderByOpenId(Map<String, Object> map);
}
