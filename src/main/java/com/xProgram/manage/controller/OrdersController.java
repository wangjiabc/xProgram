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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xProgram.manage.mapper.OrderMapper;
import com.xProgram.manage.model.Order;
import com.xProgram.manage.service.OrdersService;

@Controller
@RequestMapping("/orderService")
public class OrdersController {

   private OrdersService ordersService;
	
	@Autowired
	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	
	@RequestMapping("getAllOrders")
	public @ResponseBody List<Order>
	getAllOrders(HttpServletRequest request,
			String datepicker,@RequestParam String datepicker2){
		
		List<Order> orders=new ArrayList<>();
		
		  String type,campusAdmin;
	        Integer campusId=0;
	                
		Cookie[] cookies = request.getCookies();  
		if(cookies!=null){
				for(Cookie i:cookies){
					if(i.getName().equalsIgnoreCase("campusId"))
						campusId=Integer.parseInt(i.getValue());
			}
		}
			
		HttpSession session=request.getSession();  //ȡ��session��type�������ж��Ƿ�Ϊ���ںŹ���Ա
		type=session.getAttribute("type").toString();
		campusAdmin=session.getAttribute("campusAdmin").toString();
		
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date startTime=null;
		Date endTime=null;
		
		Date currentTime=new Date();
		
		try {
		     if(datepicker!=null){
		     startTime=df.parse(datepicker);
		     }
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				//  e2.printStackTrace();
				/*
				 * ���������ڲ�ѯ3����
				 */
				Calendar calendar = Calendar.getInstance();
		    	 calendar.add(Calendar.MONTH, -3);
		    	 startTime=calendar.getTime();
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
			 * ���������ڲ�ѯ3����
			 */
			  endTime=new Date();
			  Calendar cal=Calendar.getInstance();
			  cal.setTime(endTime);
			  cal.add(Calendar.DATE,1);
			  endTime=cal.getTime();
		}

		
		System.out.println("Orders starttime="+startTime + "    endtie="+endTime+" currentime="+currentTime);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		
		paramMap.put("campusId", campusId);
		
		if(type.equals("0")){           //����Ա��ѯ�ù��ں���������Ʒ
			orders=ordersService.selectAllOrdersByCampusId(paramMap);
		}else {                         //�̼�ֻ�ܲ�ѯ�ù��ں����Լ�����Ʒ
			paramMap.put("campusAdmin", campusAdmin);
			
			orders=ordersService.selectAllOrdersByCampusId(paramMap);
		}
		
		return orders;
		
	}
	
	
	@RequestMapping("getOrdersById")
	public @ResponseBody List<Order>
	getOrdersById(HttpServletRequest request,Integer foodId,
			String datepicker,@RequestParam String datepicker2){
		List<Order> orders=new ArrayList<>();
		
		String type,campusAdmin;
	    
		HttpSession session=request.getSession();  //ȡ��session��type�������ж��Ƿ�Ϊ���ںŹ���Ա
		type=session.getAttribute("type").toString();
		campusAdmin=session.getAttribute("campusAdmin").toString();
		
	      Integer campusId=0;
          
			Cookie[] cookies = request.getCookies();  
			if(cookies!=null){
					for(Cookie i:cookies){
						if(i.getName().equalsIgnoreCase("campusId"))
							campusId=Integer.parseInt(i.getValue());
				}
			}
		
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date startTime=null;
		Date endTime=null;
		
		Date currentTime=new Date();
		
		try {
		     if(datepicker!=null){
		     startTime=df.parse(datepicker);
		     }
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				//  e2.printStackTrace();
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

		}
	
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
	
		paramMap.put("foodId", foodId);		
		paramMap.put("campusId", campusId);
		
		orders=ordersService.selectAllOrdersByCampusId(paramMap);
		
		return orders;
		
	}

}
