package com.hc.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {
	public static void main(String[] args) throws JMSException {
		// 创建连接池
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		// 创建连接
		Connection connection = connectionFactory.createConnection();
		// 开启连接
		connection.start();
		// 创建会话
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 创建目的地
		Destination destination = session.createQueue("hc1");
		// 创建消费者
		MessageConsumer messageConsumer = session.createConsumer(destination);
		int i = 0;
		while (i < 4) {
			TextMessage textMessage = (TextMessage) messageConsumer.receive();
			session.commit();
			System.out.println(textMessage.getText());
		}
		session.close();
		connection.close();
	}
}
