/**
 * 
 */
package com.xProgram.inswept.topic.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xProgram.inswept.topic.producer.TopicSender;


/**
 * @作者 Goofy
 * @邮件 252878950@qq.com
 * @日期 2014-4-1上午10:54:11
 * @描述 测试 
 */
@Controller
@RequestMapping("/activemq")
public class ActivemqController {
	
	@Resource TopicSender topicSender;
	
	
	/**
	 * 发送消息到主题
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/insweptTopic")
	public String topicSender(@RequestParam("message")String message){
		String opt="bbbbb";
		try {
			topicSender.send("general", message);
			opt="suc";
		} catch (Exception e) {
			opt=e.getCause().toString();
		}
		return opt;
	}
	
}
