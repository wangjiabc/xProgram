package com.xProgram.inswept.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.xProgram.manage.model.Food;
import com.xProgram.manage.model.Items;
import com.xProgram.manage.model.Order;
import com.xProgram.manage.service.CartService;
import com.xProgram.manage.service.FoodService;
import com.xProgram.manage.service.OrdersService;

@Controller
@RequestMapping("/insweptCart")
public class cartController {

	private CartService cartService;

	@Autowired
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	
	@RequestMapping("/getFoodDetail")
	public @ResponseBody String
	getFoodDetail(@RequestParam Integer foodId){
		List<Items> items;
		
		int campusId=1;
		Map<String, Object> reMap=new HashMap<>();
		
		reMap.put("campusId", campusId);
		reMap.put("foodId", foodId);
		
		items=cartService.getFoodDetailById(reMap);
		
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(items);
		
		return jsonArray.toString();
			
	}
	
	@RequestMapping("/insertOrder")
	public @ResponseBody Integer
	insertOrder(@RequestParam String openId,@RequestParam Integer foodId,
			@RequestParam Integer amount){
		Map<String, Object> map=new HashMap<>();
		
		int campusId=1;
		
		Date createTime=new Date();
		
		map.put("campusId", campusId);
		map.put("openId", openId);		
		map.put("foodId", foodId);		
		map.put("amount", amount);
		map.put("createTime", createTime);
		
		int flat=cartService.insertIntoOrders(map);
		
		return flat;	
	}
	
	
	
	@RequestMapping("/getAllOrder")
	public @ResponseBody String
	getAllOrder(@RequestParam String openId){
		List<Order> orders;
		
		Map<String, Object> map=new HashMap<>();
		
		Integer campusId=1;
		
		map.put("campusId", campusId);
		
		map.put("openId", openId);
		
		orders=cartService.getAllOrder(map);
		
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(orders);
		
		return jsonArray.toString();
		
	}
	
	@RequestMapping("/delToOrders")
	public @ResponseBody Integer 
	delToOrders(@RequestParam String openId,@RequestParam Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		
		int campusId=1;
		
		int status = 0;
		
		map.put("campusId", campusId);
		map.put("openId", openId);
	
		map.put("id", id);
		status = cartService.delToOrders(map);
	
		return status;
	}
	
	@RequestMapping("/delAllOrders")
	public @ResponseBody Integer
	delAllOrders(@RequestParam String openId){
       Map<String, Object> map = new HashMap<String, Object>();
		
		int campusId=1;
		
		int status = 0;
		
		map.put("campusId", campusId);
		map.put("openId", openId);
		
		status=cartService.delALLOrders(map);
		
		return status;
	}
	
	
}

