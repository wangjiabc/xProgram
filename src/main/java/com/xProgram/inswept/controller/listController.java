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
import com.xProgram.manage.model.Food;
import com.xProgram.manage.model.NewGoods;
import com.xProgram.manage.service.FoodService;
import com.xProgram.manage.service.NewGoodsService;

@Controller
@RequestMapping("/insweptList")
public class listController {

	private NewGoodsService newGoodsService;
	
	@Autowired
	public void setNewGoodService(NewGoodsService newGoodsService) {
		// TODO Auto-generated method stub
         this.newGoodsService=newGoodsService;
	}

	@RequestMapping("newgoods")
	public @ResponseBody
	String newgoods(@RequestParam Integer start){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		List<NewGoods> foods = new ArrayList<NewGoods>();
		
		paramMap.put("campusId", 1);
		paramMap.put("start", start);
		paramMap.put("end", 10);
		foods=newGoodsService.getAllGoods(paramMap);
		
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(foods);
		
		return jsonArray.toString();
	}
}
