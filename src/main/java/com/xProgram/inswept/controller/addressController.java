package com.xProgram.inswept.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.xProgram.manage.model.Users;
import com.xProgram.manage.service.UserService;

@Controller
@RequestMapping("/insweptAddress")
public class addressController {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/getAddress")
	public @ResponseBody String
	getAddress(@RequestParam String openId){
		Map<String, Object> map=new HashMap<>();
		
		List list=new ArrayList<Users>();
		
		int campusId=1;
		
		map.put("openId", openId);
		
		list=userService.getAddress(map);
		
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(list);
		
		return jsonArray.toString();
	}
	
	@RequestMapping("/insertInfoforUser")
	public @ResponseBody Integer
	insertInfoforUser(@RequestParam String openId,@RequestParam String phone,
		   @RequestParam String name,@RequestParam String address,
			@RequestParam Integer default_address){
		  Map<String, Object> map=new HashMap<>();
		  
		  int campusId=1;
		  
		  if(default_address==1){
			  userService.setDefaultAddress0(openId);
		  }
		  
		  map.put("campusId",campusId);
		  map.put("openId", openId);
		  map.put("phone", phone);
		  map.put("name", name);
		  map.put("address", address);
		  map.put("default_address", default_address);
		  
		  int flat=userService.insertInfoforUser(map);
		  
		  return flat;
	}
	
	@RequestMapping("/upUserInfo")
	public @ResponseBody Integer
	upUserInfo(@RequestParam Integer id,@RequestParam String openId,
			@RequestParam String phone,
		   @RequestParam String name,@RequestParam String address,
			@RequestParam Integer default_address){
		  Map<String, Object> map=new HashMap<>();
		  
		  int campusId=1;
		  
		  if(default_address==1){
			  userService.setDefaultAddress0(openId);
		  }
		  
		  map.put("campusId",campusId);
		  map.put("id",id);
		  map.put("phone", phone);
		  map.put("name", name);
		  map.put("address", address);
		  map.put("default_address", default_address);
		  
		  int flat=userService.upUserInfo(map);
		  
		  return flat;
	}
	
	@RequestMapping("/delAddress")
	public @ResponseBody Integer
	delAddress(@RequestParam Integer id){

		int flat=userService.delAddress(id);
		
		return flat;
	}
	
}
