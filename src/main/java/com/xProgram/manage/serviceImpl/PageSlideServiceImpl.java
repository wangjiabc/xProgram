package com.xProgram.manage.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.manage.mapper.PageSlideMapper;
import com.xProgram.manage.model.Food;
import com.xProgram.manage.model.Items;
import com.xProgram.manage.service.PageSlideService;

@Service("/pageSlideService")
public class PageSlideServiceImpl implements PageSlideService{

	private PageSlideMapper pageSlideMapper;
	
	@Autowired
	public void setPageSlideMapper(PageSlideMapper pageSlideMapper) {
		this.pageSlideMapper = pageSlideMapper;
	}

	@Override
	public List<Items> selectAllPageSlideFood(Integer campusId) {
		// TODO Auto-generated method stub
		List<Items> pageSlides=pageSlideMapper.selectAllPageSlideFood(campusId);
		return pageSlides;
	}

	@Override
	public List<Food> selectAllPageSlide(Integer campusId) {
		// TODO Auto-generated method stub
		List<Food> pageSlides=pageSlideMapper.selectAllPageSlide(campusId);
		return pageSlides;
	}

	@Override
	public Integer upPageSlides(Items items) {
		// TODO Auto-generated method stub
		int i=pageSlideMapper.upPageSlides(items);
		return i;
	}

	@Override
	public Integer deletePageSlides(Integer id) {
		// TODO Auto-generated method stub
		int i=pageSlideMapper.deletePageSlides(id);
		return i;
	}

	@Override
	public List<Items> selectAllPageSpecialFood(Integer campusId) {
		// TODO Auto-generated method stub
		List<Items> items=pageSlideMapper.selectAllPageSpecialFood(campusId);
		return items;
	}

	@Override
	public List<Food> selectAllPageSpecial(Integer campusId) {
		// TODO Auto-generated method stub
		List<Food> foods=pageSlideMapper.selectAllPageSpecial(campusId);
		return foods;
	}

	@Override
	public Integer upPageSpecial(Items items) {
		// TODO Auto-generated method stub
		int i=pageSlideMapper.upPageSpecial(items);
		return i;
	}

	@Override
	public Integer deletePageSpecial(Integer id) {
		// TODO Auto-generated method stub
		int i=pageSlideMapper.deletePageSpecial(id);
		return i;
	}

	@Override
	public List<Items> selectAllPageGroomFood(Integer campusId) {
		// TODO Auto-generated method stub
		List<Items> items=pageSlideMapper.selectAllPageGroomFood(campusId);
		return items;
	}

	@Override
	public List<Food> selectAllPageGroom(Integer campusId) {
		// TODO Auto-generated method stub
		List<Food> foods=pageSlideMapper.selectAllPageGroom(campusId);
		return foods;
	}

	@Override
	public Integer upPageGroom(Items items) {
		// TODO Auto-generated method stub
		int i=pageSlideMapper.upPageGroom(items);
		return i;
	}

	@Override
	public Integer deletePageGroom(Integer id) {
		// TODO Auto-generated method stub
		int i=pageSlideMapper.deletePageGroom(id);
		return i;
	}


}
