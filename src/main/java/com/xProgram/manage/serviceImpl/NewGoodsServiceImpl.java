package com.xProgram.manage.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.manage.mapper.NewGoodsMapper;
import com.xProgram.manage.model.NewGoods;
import com.xProgram.manage.service.NewGoodsService;

@Service("/newGoodsService")
public class NewGoodsServiceImpl implements NewGoodsService{

	private NewGoodsMapper newGoodsMapper;
	
	@Autowired
	public void setNewGoodsMapper(NewGoodsMapper newGoodsMapper) {
		this.newGoodsMapper = newGoodsMapper;
	}
	
	@Override
	public List<NewGoods> getAllGoods(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return newGoodsMapper.getAllGoods(paramMap);
	}


}
