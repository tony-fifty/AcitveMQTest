package com.hc.producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducer {
	public static void main(String[] args) throws JMSException {
		// 创建连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		// 创建连接
		Connection connection = connectionFactory.createConnection();
		// 发送端可以不需要,接收端必须启动连接
		connection.start();
		// 创建回会话
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 创建目的地
		Destination destination = session.createQueue("hc1");
		// 创建生产者
		MessageProducer message = session.createProducer(destination);
		for (int i = 0; i < 4; i++) {
			// 发送消息
			// TextMessage textMessage = session.createTextMessage("发送" + i);
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText("hc1发送" + i);
			// 通过消息生产者发出消息
			message.send(textMessage);
		}
		session.commit();
		session.close();
		connection.close();
	}
}
