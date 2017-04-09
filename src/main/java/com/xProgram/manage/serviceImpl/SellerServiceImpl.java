package com.xProgram.manage.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xProgram.manage.mapper.SellerMapper;
import com.xProgram.manage.model.Sellers;
import com.xProgram.manage.service.SellerService;

/**
 * 商家账号管理
 * @author xiaowei
 *
 */
@Service("sellerService")
public class SellerServiceImpl implements SellerService {

	private SellerMapper sellerMapper;//操作用户信息

	@Autowired
	public void setSellerMapper(SellerMapper sellerMapper) {
		this.sellerMapper = sellerMapper;
	}



	
	public Sellers selectByCampusAdmin(String campusAdmin) {
		return sellerMapper.selectByCampusAdmin(campusAdmin);
	}


	public void updateLastLoginTime(Date date, String campusAdmin) {
		sellerMapper.updateLastLoginTime(date,campusAdmin);
		
	}
	
	public void addASeller(Sellers seller)
	{
		sellerMapper.insertSellective(seller);
	}


	public Sellers selectByCampusId(String campusAdmin) {
		// TODO Auto-generated method stub
		return sellerMapper.selectByCampusId(campusAdmin);
	}

    public List<Sellers> getCampusAdmin(String campusAdmin) {
		return sellerMapper.getCampusAdmin(campusAdmin);
	}

	@Override
	public List<Sellers> getAllCampusAdmin() {
		// TODO Auto-generated method stub
		return sellerMapper.getAllCampusAdmin();
	}

    /*
     * (non-Javadoc)
     * @see com.xProgram.manage.service.SellerService#selectRepeatAdmin(java.lang.String)
     * 查找重复用户名
     */
	@Override
	public int selectRepeatAdmin(String campusAdmin) {
		// TODO Auto-generated method stub
		return sellerMapper.selectRepeatAdmin(campusAdmin);
	}




	@Override
	public int selectMaxCityId() {
		// TODO Auto-generated method stub
		return sellerMapper.selectMaxCityId();
	}
}
