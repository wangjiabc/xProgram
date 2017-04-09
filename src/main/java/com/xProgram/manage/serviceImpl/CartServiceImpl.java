package com.xProgram.manage.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.inswept.controller.orderController;
import com.xProgram.manage.mapper.CartMapper;
import com.xProgram.manage.mapper.OrderMapper;
import com.xProgram.manage.model.Items;
import com.xProgram.manage.model.Order;
import com.xProgram.manage.service.CartService;

@Service("/cartService")
public class CartServiceImpl implements CartService{

	private CartMapper cartMapper;
	
	private OrderMapper orderMapper;
	
	@Autowired
	public void setCartMapper(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}
	
	@Autowired
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
	
	@Override
	public List<Items> getFoodDetailById(Map<String, Object> renMap) {
		// TODO Auto-generated method stub
		return cartMapper.getFoodDetailById(renMap);
	}


	@Override
	public Integer insertIntoOrders(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cartMapper.insertIntoOrders(map);
	}


	@Override
	public List<Order> getAllOrder(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderMapper.getAllOrderByOpenId(map);
	}

	@Override
	public Integer delToOrders(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cartMapper.delToOrders(map);
	}

	@Override
	public Integer delALLOrders(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cartMapper.delALLOrders(map);
	}

}
