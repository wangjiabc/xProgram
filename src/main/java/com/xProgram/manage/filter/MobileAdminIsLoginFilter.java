package com.xProgram.manage.filter;

import java.io.IOException;   

import javax.servlet.Filter;   
import javax.servlet.FilterChain;   
import javax.servlet.FilterConfig;   
import javax.servlet.ServletException;   
import javax.servlet.ServletRequest;   
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;   
  
public class MobileAdminIsLoginFilter implements Filter {   
    public FilterConfig config2=null;
    @Override  
    public void destroy() {   
  
    }   
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response,   
            FilterChain chain) throws IOException, ServletException {   
    	  HttpServletRequest hrequest = (HttpServletRequest)request;
	        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
	        
	        String loginStrings = config2.getInitParameter("loginStrings");        
	        String includeStrings = config2.getInitParameter("includeStrings");    
	        String redirectPath = hrequest.getContextPath() + config2.getInitParameter("redirectPath");
	        String disabletestfilter = config2.getInitParameter("disabletestfilter");
	        

	        String[] loginList = loginStrings.split(";");
	        String[] includeList = includeStrings.split(";");
	        
	        if (!IsLoginFilter.isContains(hrequest.getRequestURI(), includeList)) {
	            chain.doFilter(request, response);
	            return;
	        }
	        
	        if (IsLoginFilter.isContains(hrequest.getRequestURI(), loginList)) {//
	            chain.doFilter(request, response);
	            return;
	        }
	        
	        String user = ( String ) hrequest.getSession().getAttribute("campusAdmin");
	        String openId=( String ) hrequest.getSession().getAttribute("openId");
	        
	        if (user == null||openId!=null) {
	        	HttpSession session = hrequest.getSession();	        	  
	        	session.invalidate();         //����΢�ŵ�¼��������session�ٵ�½����Աҳ��
	        	System.out.println("clear session user="+user+"    openId="+openId);
	        	wrapper.sendRedirect(redirectPath);
	            return;
	        }else {
	        	System.out.println("true user="+user+"    openId="+openId);
	            chain.doFilter(request, response);
	            return;
	        }   
    }   
  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {   
    	config2 = filterConfig;
    }
}   