package xProgram;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mybatis {
	public static ApplicationContext ctx;  
	 
	static{
	 ctx=new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");  
	}
}
