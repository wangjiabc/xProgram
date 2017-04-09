package com.xProgram.manage.service;

import java.util.List;

import com.xProgram.manage.model.Items;

public interface ItemsService {
	List<Items> getAllFoods(Integer campusId);
}
