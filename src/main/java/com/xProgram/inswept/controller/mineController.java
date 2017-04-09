package com.xProgram.inswept.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/insweptMine")
public class mineController {
	
	@RequestMapping("/aaa")
	public @ResponseBody
	String aaa(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
		return session.getAttribute("openId").toString();
	}
}
