package com.xProgram.inswept.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xProgram.manage.model.Food;
import com.xProgram.manage.model.Items;
import com.xProgram.manage.service.FoodService;
import com.xProgram.manage.service.ItemsService;
import com.xProgram.manage.service.PageSlideService;

@Controller
@RequestMapping("/insweptIndex")
public class indexController {
	
	private PageSlideService pageSlideService;
	
	private FoodService foodService;
	
	@Autowired
	public void setPageSlideService(PageSlideService pageSlideService) {
		this.pageSlideService = pageSlideService;
	}
	
	@Autowired
	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}

	
	private final int campusId=1;
	
	//�����õ�Ƭ
	@RequestMapping("/banner")
	public @ResponseBody
	String banner() {
		List<Items> items;
		
		items=pageSlideService.selectAllPageSlideFood(campusId);
		
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(items);
		
		return jsonArray.toString();
	}
	
	@RequestMapping("/getPageSlides")
	public @ResponseBody
	List<Food> getPageSlides() {
		List<Food> pageSlides;
		
		pageSlides=pageSlideService.selectAllPageSlide(campusId);
	
		return pageSlides;
	}
	
	@RequestMapping("getAllFood")
	public @ResponseBody
	JSONArray getAllFoodCategories4Client() {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("campusId", campusId);
		
		List<Food> food = foodService.getAllFoods(paramMap);

		return (JSONArray) JSON.toJSON(food);
	}
	
	@RequestMapping("upPageSlides")
	public @ResponseBody
	Integer upPageSlides(@RequestParam Integer id,@RequestParam Integer foodId){
		Items items=new Items();
		
		items.setId(id);
		items.setFoodId(foodId);
		
		int i=pageSlideService.upPageSlides(items);
	    
		return 1;
	}
	
	@RequestMapping("deletePageSlides")
	public @ResponseBody
	Integer deletePageSlides(@RequestParam Integer id){
		int i=pageSlideService.deletePageSlides(id);
		
		return i;
	}
	
	//������Ʒ
	@RequestMapping("/newgoods")
	public @ResponseBody
	String newGoods(){
		List<Items> items;
		
		items=pageSlideService.selectAllPageSpecialFood(campusId);
		
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(items);
		
		return jsonArray.toString();
	}
	
	@RequestMapping("/getnewgoods")
	public @ResponseBody
	List<Food> getNewGoods() {
		List<Food> pageSpecial;
		
		pageSpecial=pageSlideService.selectAllPageSpecial(campusId);
	
		return pageSpecial;
	}
	
	@RequestMapping("uppagespecial")
	public @ResponseBody
	Integer upPageSpecial(@RequestParam Integer id,@RequestParam Integer foodId){
		Items items=new Items();
		
		items.setId(id);
		items.setFoodId(foodId);
		
		int i=pageSlideService.upPageSpecial(items);
	    
		return 1;
	}
	
	@RequestMapping("deletepagespecial")
	public @ResponseBody
	Integer deletePageSpecial(@RequestParam Integer id){
		int i=pageSlideService.deletePageSpecial(id);
		
		return i;
	}
	
	//�Ƽ���Ʒ
	@RequestMapping("/hotgoods")
	public @ResponseBody
	String hotGoods(){
		List<Items> items;
		
		items=pageSlideService.selectAllPageGroomFood(campusId);
		
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(items);
		
		return jsonArray.toString();
	}
	
	@RequestMapping("/gethotgoods")
	public @ResponseBody
	List<Food> getHotGoods() {
		List<Food> pageGroom;
		
		pageGroom=pageSlideService.selectAllPageGroom(campusId);
	
		return pageGroom;
	}
	
	@RequestMapping("uppageGroom")
	public @ResponseBody
	Integer upPageGroom(@RequestParam Integer id,@RequestParam Integer foodId){
		Items items=new Items();
		
		items.setId(id);
		items.setFoodId(foodId);
		
		int i=pageSlideService.upPageGroom(items);
	    
		return 1;
	}
	
	@RequestMapping("deletepageGroom")
	public @ResponseBody
	Integer deletePageGroom(@RequestParam Integer id){
		int i=pageSlideService.deletePageGroom(id);
		
		return i;
	}
}
