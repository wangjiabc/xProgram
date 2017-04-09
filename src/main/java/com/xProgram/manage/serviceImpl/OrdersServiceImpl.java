package com.xProgram.manage.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.manage.mapper.OrderMapper;
import com.xProgram.manage.model.Order;
import com.xProgram.manage.service.OrdersService;

@Service("/ordersService")
public class OrdersServiceImpl implements OrdersService{

	private OrderMapper orderMapper;
	
	@Autowired
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
	
	@Override
	public List<Order> selectAllOrdersByCampusId(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return orderMapper.selectAllOrdersByCampusId(paramMap);
	}

}
