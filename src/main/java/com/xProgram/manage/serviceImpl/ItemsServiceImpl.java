package com.xProgram.manage.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.manage.mapper.ItemsMapper;
import com.xProgram.manage.model.Items;
import com.xProgram.manage.service.ItemsService;

@Service("/itemsService")
public class ItemsServiceImpl implements ItemsService{

	private ItemsMapper itemsMapper;
	
	@Autowired
	public void setItemsMapper(ItemsMapper itemsMapper) {
		this.itemsMapper = itemsMapper;
	}
	
	@Override
	public List<Items> getAllFoods(Integer campusId) {
		// TODO Auto-generated method stub
		return itemsMapper.getAllFoods(campusId);
	}

}
