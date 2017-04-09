package com.xProgram.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xProgram.manage.model.Sellers;
import com.xProgram.manage.service.SellerService;
import com.xProgram.manage.tools.Constants;
import com.xProgram.manage.tools.Md5;

@Controller
@RequestMapping("/seller")
public class SellerController {
	private SellerService sellerService;

	public SellerService getSellerService() {
		return sellerService;
	}

	@Autowired
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}
	
	

	
	
	/**
	 * 鍟嗗鐧诲綍
	 * @param campusAdmin
	 * @param password
	 * */
	@RequestMapping("/toLogin")
	public @ResponseBody Map<String, Object> toLogin(
			@RequestParam String campusAdmin, @RequestParam String password,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (campusAdmin != null && password != null
				&& !campusAdmin.trim().equals("")
				&& !password.trim().equals("")) {
			Sellers sellers = sellerService.selectByCampusAdmin(campusAdmin);
			if (sellers != null) {
				if (sellers.getPassword().equals(Md5.GetMD5Code(password))) {
					map.put(Constants.STATUS, Constants.SUCCESS);
					map.put(Constants.MESSAGE, "登陆成功");
					map.put("type", sellers.getType());
					HttpSession session = request.getSession();
					session.setAttribute("type", sellers.getType());
					session.setAttribute("campusAdmin",
							sellers.getCampusAdmin());
					session.setAttribute("cityId", sellers.getCityId());
					Date date = new Date();
					sellerService.updateLastLoginTime(date, campusAdmin);
				} else {
					map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "用户名或者密码错误");
				}
			} else {
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "用户名或者密码错误");
			}
		}

		return map;
	}
	
	/**
	 * 鏍规嵁鍟嗗id鏌ユ壘鍟嗗鏁版嵁
	 *
	 * */
	
	@RequestMapping("/getSellerById")
	public @ResponseBody Map<String, Object> getSellerById(String campusAdmin)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Sellers sellers = sellerService.selectByCampusAdmin(campusAdmin);
		Sellers sellercampus=sellerService.selectByCampusId(campusAdmin);
		map.put("sellercampus", sellercampus);
		map.put("seller",sellers);		
		return map;
	}
	
	/**
	 * 妫�鏌ヨ璐﹀彿鏄惁娉ㄥ唽杩�
	 * @param campusAdmin
	 * @return
	 */
	
	@RequestMapping("/checkSellerIsExist")
	public @ResponseBody Map<String, Object> checkSellerIsExist(String campusAdmin)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Sellers sellers = sellerService.selectByCampusAdmin(campusAdmin);
		if(sellers==null)
		{
			map.put(Constants.STATUS, Constants.SUCCESS);
			map.put(Constants.MESSAGE, "璇ョ敤鎴峰悕涓嶅瓨鍦紝鍙互娉ㄥ唽");
		}
		else
		{
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "璇ョ敤鎴峰悕宸插瓨鍦紝璇锋崲涓�涓悕瀛�");			
		}
		
		return map;	
	}
	
	
	@RequestMapping("/getCampusAdmin")
	public @ResponseBody JSONArray getCampusAdmin(HttpServletRequest request){
		List<Sellers> sellers;
		
		HttpSession session=request.getSession();  //取得session的type变量，判断是否为公众号管理员
		String campusAdmin=session.getAttribute("campusAdmin").toString();
		String type=session.getAttribute("type").toString();
		
		
		if(type.equals("0")){
          sellers=sellerService.getAllCampusAdmin();
		}else {
		  sellers=sellerService.getCampusAdmin(campusAdmin);
		}
		return (JSONArray) JSON.toJSON(sellers);
		
	}
}