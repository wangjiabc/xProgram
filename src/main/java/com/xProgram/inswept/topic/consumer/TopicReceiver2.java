/**
 * 
 */
package com.xProgram.inswept.topic.consumer;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.stereotype.Component;

/**
 * @描述 Topic消息监听器
 */
@Component
public class TopicReceiver2 implements MessageListener{


	@Override
	public void onMessage(Message message) {
		String result;
		  try {
			if (message instanceof ActiveMQTextMessage) {
			    ActiveMQTextMessage amqMessage = (ActiveMQTextMessage) message;
			    result=amqMessage.getText();
			} else {
			    BytesMessage bm = (BytesMessage) message;
			    byte data[] = new byte[(int) bm.getBodyLength()];
			    bm.readBytes(data);
			    result=new String(data);
			}		
			System.out.println("TopicReceiver2接收到消息:"+result);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
