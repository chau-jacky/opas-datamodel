package com.opas.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.opas.model.NotificationMessage;

@Component
public class NotificationMessageSender {

	@Autowired
	JmsTemplate jmsNotificationTemplate;
	
	@Autowired
	JmsTemplate jmsPubSubTemplate;

	public void sendMessage(final NotificationMessage notificationMessage) {

		jmsNotificationTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(notificationMessage);
				return objectMessage;
			}
		});
		
		jmsPubSubTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(notificationMessage);
				return objectMessage;
			}
		});
		
	}

}
