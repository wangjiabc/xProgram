package com.xProgram.manage.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.manage.mapper.WxInfoMapper;
import com.xProgram.manage.model.WxInfo;
import com.xProgram.manage.service.WxInfoService;

@Service("wxinfoService")
public class WxInfoServiceImpl implements WxInfoService{

	private WxInfoMapper wxInfoMapper;
	
	@Autowired
	public void setWxInfoMapper(WxInfoMapper wxInfoMapper) {
		this.wxInfoMapper = wxInfoMapper;
	}
	
	@Override
	public WxInfo getAppId(int campusId) {
		// TODO Auto-generated method stub
		return wxInfoMapper.getAppId(campusId);
	}

}
