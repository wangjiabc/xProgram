package com.xProgram.manage.service;

import java.util.List;

import com.xProgram.manage.model.Food;
import com.xProgram.manage.model.Items;

public interface PageSlideService {
	
	List<Items> selectAllPageSlideFood(Integer campusId);
	
	List<Food> selectAllPageSlide(Integer campusId);
	
	Integer upPageSlides(Items items);
	
	Integer deletePageSlides(Integer id);
	
	 List<Items> selectAllPageSpecialFood(Integer campusId);
		
		List<Food> selectAllPageSpecial(Integer campusId);
		
		Integer upPageSpecial(Items items);
		
		Integer deletePageSpecial(Integer id);
		
	    List<Items> selectAllPageGroomFood(Integer campusId);
		
		List<Food> selectAllPageGroom(Integer campusId);
		
		Integer upPageGroom(Items items);
		
		Integer deletePageGroom(Integer id);	
}
