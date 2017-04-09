package com.xProgram.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xProgram.manage.model.Sellers;
import com.xProgram.manage.service.SellerService;
import com.xProgram.manage.tools.Constants;
import com.xProgram.manage.tools.Md5;
import com.xProgram.manage.tools.verifycode.Captcha;
import com.xProgram.manage.tools.verifycode.SpecCaptcha;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private String verifyCode;
	
	private SellerService sellerService;
	
	@Autowired
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}
	
	/*
	 * ������֤����
	 */
	@RequestMapping(value="getYzm",method=RequestMethod.GET)
	public void getYzm(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/jpeg");  
	          
	        //���������ִ�  
	        Captcha captcha = new SpecCaptcha(120,25,4); 

	        //����ͼƬ  
	        captcha.out(response.getOutputStream());
            verifyCode=captcha.text().toLowerCase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping("testName")
	public @ResponseBody Map<String, Object>
	testName(@RequestParam String username){
		Map<String, Object> map=new HashMap<>();
		
		int repeat=sellerService.selectRepeatAdmin(username);
		
		if(username.equals("")){
			map.put("data", "�û������ܿ�");
			return map;
		}
		
		if(username.length()<4||username.length()>16){
			map.put("data", "�û���������4-16���ַ�֮�䣨����4��16��");
			return map;
		}
		
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";  
		Pattern   p   =   Pattern.compile(regEx);     
	    Matcher   m   =   p.matcher(username); 
	      
	    if(m.find()){
	    	map.put("data", "�û������зǷ��ַ�");
			return map;
	    }
	    
		if(repeat==1){
			map.put("data", "���û����ѱ�ע�ᣬ��ʹ�������û���ע��");
			return map;
		}else{
			map.put("data", "succeed");
			return map;
		}
	}
	
	@RequestMapping("testPassword")
	public @ResponseBody Map<String, Object>
	testPassword(@RequestParam String password){
        Map<String, Object> map=new HashMap<>();
		
		if(password.equals("")){
			map.put("data", "���벻�ܿ�");
			return map;
		}
		
		if(password.length()<6||password.length()>16){
			map.put("data", "'�����ĳ���Ӧ����6-16���ַ�֮��");
			return map;
		}
		
		map.put("data", "succeed");
		
		return map;
		
	}
	
	/**
     * �绰������֤
     * 
     * @param  str
     * @return ��֤ͨ������true
     */
	 public static boolean isPhone(String str) { 
        Pattern p1 = null,p2 = null;
        Matcher m = null;
        boolean b = false;  
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // ��֤�����ŵ�
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // ��֤û�����ŵ�
        if(str.length() >9)
        {   m = p1.matcher(str);
           b = m.matches();  
        }else{
            m = p2.matcher(str);
           b = m.matches(); 
        }  
        return b;
     }
	
	@RequestMapping("/testPhone")
	public @ResponseBody Map<String, Object>
	testPhone(@RequestParam String telephone){
	    Map<String, Object> map=new HashMap<>();
		
		if(telephone.equals("")){
			map.put("data", "�绰���벻�ܿ�");
			return map;
		}
		
		if(!isPhone(telephone)){
			map.put("data", "��������ȷ�ĵ绰����");
			return map;
		}
		
        map.put("data", "succeed");
		
		return map;
	}
	
   @RequestMapping("/testEmail")
   public @ResponseBody Map<String, Object>
   testEmail(@RequestParam String email){
	   Map<String, Object> map=new HashMap<>();
	   
	   if(email.equals("")){
			map.put("data", "�ŵ���ַ���ܿ�");
			return map;
		}
	   
	   String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";  
		Pattern   p   =   Pattern.compile(regEx);     
	    Matcher   m   =   p.matcher(email); 
	      
	    if(m.find()){
	    	map.put("data", "�ŵ���ַ���зǷ��ַ�");
			return map;
	    }
	    
        map.put("data", "succeed");
		
		return map;
   }
	
   @RequestMapping("insert")
   public @ResponseBody Map<String, Object>
   insert(@RequestParam String username,@RequestParam String password,
		   @RequestParam Integer telephone,@RequestParam String email,
		   @RequestParam String regtlx){
	   Map<String, Object> map=new HashMap<>();
	   
	   System.out.println("use="+username+"pw="+password
			   +"tp="+telephone+"mail="+email);
	   
	   if(regtlx.equals("")){
		   map.put("data", "regtlx_null");
		   return map;
	   }
	   
	   if(!regtlx.equals(verifyCode)){
		   map.put("data", "regtlx_error");   //��֤������
		   verifyCode=null;
		   return map;
	   }
	   
	   String campusAdmin=username;
	   String address=email;
	   Integer cityId=sellerService.selectMaxCityId();
	   Date registerTime=new Date();
	   
	   try {		
				String passwordMd5 = Md5.GetMD5Code(password);
				Sellers seller = new Sellers();
				seller.setCampusAdmin(campusAdmin);
				seller.setPassword(passwordMd5);
				seller.setType((short) 1);     //��ע������Ϊ�ǹ���Ա
				seller.setCityId(cityId+1);    //���ں�idΪ�������ں�id����1
				seller.setTelePhone(telephone);
				seller.setAddress(address);
				seller.setRegisterTime(registerTime);
				
				sellerService.addASeller(seller);	
				
				map.put("data", "succeed");
				return map;
			
			}
		    catch (Exception e) {
		    	e.printStackTrace();
		    	
	            map.put("data", "false");
	            return map;
		    }
   }
   
}
