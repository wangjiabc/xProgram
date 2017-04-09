package com.xProgram.manage.mapper;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.NewGoods;

public interface NewGoodsMapper {

	List<NewGoods> getAllGoods(Map<String, Object> paramMap);  
	
}
