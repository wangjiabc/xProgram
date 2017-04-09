package com.xProgram.manage.mapper;

import java.util.List;

import com.xProgram.manage.model.Items;

public interface ItemsMapper {

	List<Items> getAllFoods(Integer campusId);
	
}
