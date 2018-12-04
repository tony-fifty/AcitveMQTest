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
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("hc");
		MessageProducer messageProducer = session.createProducer(destination);
		for (int i = 0; i < 3; i++) {
			TextMessage message = session.createTextMessage("hc--" + i);
			messageProducer.send(message);
		}
		session.commit();
		session.close();
		connection.close();
	}
}
