package com.xProgram.manage.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.xProgram.manage.tools.XssHttpServletRequestWraper;

/**
 * ��ֹxss����������
 * @author xiaowei
 *
 */
public class XssFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		  chain.doFilter(new XssHttpServletRequestWraper((HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {
		
	}

}
