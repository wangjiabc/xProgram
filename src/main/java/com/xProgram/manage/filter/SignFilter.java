package com.xProgram.manage.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.xProgram.manage.tools.Constants;
import com.xProgram.manage.tools.Sign;

/**
 * 鐎圭偠顢戠�佃甯撮崣锝堢箻鐞涘瞼顒烽崥锟�
 * 
 * @author xiaowei
 * @copyght 閸娾�茬埃缁夋垶濡ч張澶愭閸忣剙寰�
 */
public class SignFilter implements Filter {

//	Logger logger = Logger.getLogger(SignFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		@SuppressWarnings("unchecked")
		HashMap<String, String[]> map = (HashMap<String, String[]>) request
				.getParameterMap();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSON.toJSONString(resultMap);


	    chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
