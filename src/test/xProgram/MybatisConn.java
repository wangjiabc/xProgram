package xProgram;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.xProgram.manage.mapper.AffairMapper;
import com.xProgram.manage.model.Affair;
import com.xProgram.manage.service.AffairService;
import com.xProgram.manage.serviceImpl.AffairServiceImpl;  

@Configuration
@ComponentScan
public class MybatisConn {
	
  private AffairService affairService;
	
  @Autowired
  public void setAffairService(AffairService affairService) {
	this.affairService=affairService;
}
  
    @Test
	public	void affair1(ServletRequest request) throws Exception{
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", 1);
		paramMap.put("val", "dddddd");
		
	//	AffairMapper affairMapper=(AffairMapper) Mybatis.ctx.getBean(AffairMapper.class);
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(httpRequest.getSession()
						.getServletContext());
		
		System.out.println(affairService);
		
		try{

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	//	int i=affair.getAmount();

	}





}
