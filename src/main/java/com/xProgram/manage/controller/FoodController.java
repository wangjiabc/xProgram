package com.xProgram.manage.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xProgram.manage.model.FoodCategory;
import com.xProgram.manage.model.FoodDetail;
import com.xProgram.manage.model.Food;
import com.xProgram.manage.service.FoodService;
import com.xProgram.manage.tools.Constants;


/**
 * 妞嬬喎鎼ч幒褍鍩楃猾锟�
 * 
 * @author 濞堝じ绗� 2014/12/16
 */
@Controller
@RequestMapping("/service")
public class FoodController {
	private static final Logger logger = LoggerFactory.getLogger("FoodController");
	
	private FoodService foodService;


	protected static final Logger LOG = LoggerFactory
			.getLogger(FoodController.class);


	@Autowired
	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}



	@RequestMapping("deleteFood")
	public @ResponseBody
	Map<String, Object> deleteFood(@RequestParam String foodId) {
			
		Map<String, Object> map = new HashMap<String, Object>();
        logger.info("foodID="+foodId);
        
        int currenStatus=0; //商品审核状态
        
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();

			int status = 0;
			String[] foodIdString = foodId.split(",");
			System.out.println("foodIdstring="+foodIdString);
			// 娑擄拷濞嗏�冲灩闂勩倕顦挎稉顏堟祩妞嬶拷
			for (String foodsString : foodIdString) {
				currenStatus=foodService.selectStatusByKey(Integer.valueOf(foodsString));
				if(currenStatus==1){
					break;
				}
				paramMap.put("foodId", Integer.valueOf(foodsString));
				status = foodService.deleteFoodByPrimaryKey(paramMap);
				logger.info("status="+status);
				
				if (status == 0 || status == -1) {
					break;
				}
			}

			System.out.println("status="+status+"   currenstatus="+currenStatus);
			
			if (status != -1) {
				if (status != 0) {
					map.put(Constants.STATUS, Constants.SUCCESS);
					map.put(Constants.MESSAGE, "删除成功");
				}else if(currenStatus==1){
					map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "已审核的商品不能删除");
				} else {
					map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "删除失败");
				}
			} else {
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "删除失败");
			}

		} catch (Exception e) {

		}

		return map;
	}




	/**
	 * 閼惧嘲褰囬幍锟介張澶屾畱妞嬬喎鎼�
	 * 
	 * @return
	 */
	@RequestMapping("/getAllFoods")
	public @ResponseBody
	List<Food> getAllFoods(HttpServletRequest request,@RequestParam String datepicker,@RequestParam String datepicker2) {
		List<Food> foods = new ArrayList<Food>();
        String type,campusAdmin;
        Integer campusId=0;
                
		Cookie[] cookies = request.getCookies();  
		if(cookies!=null){
			for(Cookie i:cookies){
				if(i.getName().equalsIgnoreCase("campusId"))
					campusId=Integer.parseInt(i.getValue());
			}
		}
		
		HttpSession session=request.getSession();  //取得session的type变量，判断是否为公众号管理员
		type=session.getAttribute("type").toString();
		campusAdmin=session.getAttribute("campusAdmin").toString();
		
		
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date startTime=null;
		Date endTime=null;
		
		try {
		     if(datepicker!=null){
		     startTime=df.parse(datepicker);
		     }
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				//  e2.printStackTrace();
				/*
				 * 涓嶈缃棩鏈熸煡璇�3涓湀
				 */
			}
		
		try {
			if(datepicker2!=null){
			 endTime=df.parse(datepicker2);
			 Calendar cal=Calendar.getInstance();
			 cal.setTime(endTime);
			 cal.add(Calendar.DATE,1);
			 endTime=cal.getTime();
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			/*
			 * 涓嶈缃棩鏈熸煡璇�3涓湀
			 */
			 endTime=new Date();
			  Calendar cal=Calendar.getInstance();
			  cal.setTime(endTime);
			  cal.add(Calendar.DATE,1);
			  endTime=cal.getTime();
		}

		
		logger.info("start="+datepicker+"         end="+datepicker2);
		
		Date currentTime=new Date();

		logger.info("starttime="+startTime + "    endtie="+endTime+" currentime="+currentTime);

		try {
			Map<String, Object> renMap = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			renMap.put("currentTime", currentTime);
			
			renMap.put("campusId", campusId);
			
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
			
			paramMap.put("campusId", campusId);
			
			renMap.put("campusAdmin", campusAdmin);
			foods=foodService.getAllFoods(paramMap);
		} catch (Exception e) {
			e.getStackTrace();
		}

		
		
		return foods;
	}

	/*
	 * 添加或更新商品
	 */
	@RequestMapping(value = "/updateFoods")
	public String updateFoods(@RequestParam MultipartFile[] myfile,
			HttpServletRequest request) {
		   
		Integer campusId=0;
		Integer foodCount = null;
		Integer foodAllCount=null;
		
		
		try {
			String campusName=request.getParameter("campusName");
					
			
			String campusAdmin=request.getParameter("campusAdmin");
			
			Float price = Float.valueOf(request.getParameter("price")); // 閼惧嘲褰囨禒閿嬬壐
			String name = request.getParameter("foodName"); // 閼惧嘲褰囨鐔锋惂閸氬秶袨

			String foodFlag = request.getParameter("foodTag"); // 妞嬬喎鎼ч弽鍥╊劮
			Integer categoryId = Integer.valueOf(request
					.getParameter("parentId")); // 閼惧嘲褰囬崚鍡欒Id
			Float primeCost = null;

			
			String message = request.getParameter("message");
			System.out.println(message);
			String temp1 = request.getParameter("primeCost"); // 閼惧嘲褰囬幋鎰拱娴狅拷
			String temp2 = request.getParameter("foodCount"); // 閼惧嘲褰囨鐔锋惂閺佷即鍣�
			 
		    
		     Cookie[] cookies = request.getCookies();  
			
			if(cookies!=null){
				for(Cookie i:cookies){
					if(i.getName().equalsIgnoreCase("campusId"))
						campusId=Integer.parseInt(i.getValue());
				}
			} // 閼惧嘲褰囬弽鈥冲隘
			if (temp1 != null && !temp1.trim().equals("")) {
				primeCost = Float.valueOf(request.getParameter("primeCost"));
			}

			if (temp2 != null && !temp2.trim().equals("")) {
				foodCount = Integer.valueOf(request.getParameter("foodCount"));
				foodAllCount = Integer.valueOf(request.getParameter("foodCount"));
			}

			//商品价格错误
			if(primeCost>price||price<0){
				return "redirect:/error/updateFoodPrice.html";
			}
			
			String imageUrl=request.getParameter("imgUrl");

			Food food = new Food(campusId,campusAdmin,campusName, name, price,
					imageUrl,foodFlag, foodCount,foodAllCount, categoryId, primeCost);
			food.setMessage(message);
			food.setFoodCount(foodCount);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("campusId", campusId);
			logger.info("campusid="+campusId);
			
			Food orignFood=null;
			Integer foodId=0;
			try{
			  foodId=Integer.parseInt(request.getParameter("foodId"));
			  orignFood = foodService.selectFoodByPrimaryKey(foodId); // 閺屻儳婀呯拠銉╊棨閸濅焦妲搁崥锕�鐡ㄩ崷锟�
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			logger.info("foodid="+foodId);
			
			
			int flag = 0;
			if (orignFood == null) {
				// 插入新商品记录
				flag = foodService.insertFoodSelective(food);
			
			} else {
				//修改商品记录
				food.setFoodId(foodId);
				flag=foodService.updateFoodByPrimaryKeySelective(food);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/error/updateFoods.html";
		}
		
		return "redirect:/admin.html?backurl=food.html";
		
	}


	@RequestMapping("updateStatus")
	public @ResponseBody
	String updateStatus(@RequestParam Integer foodId){
		Integer status=foodService.updateFoodStatusByPrimaryKey(foodId);
		return status.toString();
	}
	
	@RequestMapping("addFoodCountById")
	public @ResponseBody
	String addFoodCountById(@RequestParam Integer foodId,
			@RequestParam Integer foodCount){
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("foodId", foodId);
		paramMap.put("foodCount", foodCount);
		Integer addFood=foodService.addFoodCountById(paramMap);
		return  addFood.toString();
	}
	
	@RequestMapping("getAllFoodCategories4Client")
	public @ResponseBody
	JSONArray getAllFoodCategories4Client() {
		

		List<FoodCategory> foodCategories = foodService
				.getAllFoodCategories();

		return (JSONArray) JSON.toJSON(foodCategories);
	}
	
	@RequestMapping("getFoodDetail")
	public @ResponseBody
	List<FoodDetail> getFoodDetail(@RequestParam String foodId2){
		Integer foodId=Integer.parseInt(foodId2);
		
		List<FoodDetail> foodDetail=foodService.getDetailByKey(foodId);
		
		return foodDetail;
		
	}
	
	@RequestMapping("insertFoodDetail")
	public @ResponseBody
	Integer insertFoodDetail(@RequestParam String foodId,@RequestParam String imgUrl,
			@RequestParam String useTime,@RequestParam String besPoke,
			@RequestParam String rule, @RequestParam String cue){
		
		 FoodDetail foodDetail=new FoodDetail();
		
		 int foodId2=Integer.parseInt(foodId);
		 foodDetail.setFoodId(foodId2);
		 foodDetail.setImgUrl(imgUrl);
		 foodDetail.setUseTime(useTime);
		 foodDetail.setBesPoke(besPoke);
		 foodDetail.setRule(rule);
		 foodDetail.setCue(cue);
		
		 int flag=foodService.insertFoodDetailSelect(foodDetail);
		 
		 return flag;
	}
	
	@RequestMapping("getFoodCountById")
	public @ResponseBody
	Integer getFoodCountById(@RequestParam Integer foodId){
		
		int foodCount=foodService.getFoodCountById(foodId);
		
		return foodCount;
	}
	 
}