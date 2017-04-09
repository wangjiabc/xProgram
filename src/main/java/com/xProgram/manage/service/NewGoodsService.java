package com.xProgram.manage.service;

import java.util.List;
import java.util.Map;

import com.xProgram.manage.model.NewGoods;

public interface NewGoodsService {
	List<NewGoods> getAllGoods(Map<String, Object> paramMap);  
}
