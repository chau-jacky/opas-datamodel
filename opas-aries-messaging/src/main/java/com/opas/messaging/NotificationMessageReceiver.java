package com.opas.messaging;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.opas.model.NotificationMessage;
import com.opas.service.NotificationMessageService;

@Component
public class NotificationMessageReceiver {

	static final Logger LOG = LoggerFactory.getLogger(NotificationMessageReceiver.class);
	private static final String ORDER_QUEUE = "opas-notification-queue";
	
	@Autowired
	NotificationMessageService notificationMessageService;

	
	@JmsListener(destination = ORDER_QUEUE)
	public void receiveMessage(final Message<NotificationMessage> message) throws JMSException {
		LOG.info("----------------------------------------------------");
		MessageHeaders headers =  message.getHeaders();
		LOG.info("Notification Message Application : headers received : {}", headers);
		
		NotificationMessage order = message.getPayload();
		LOG.info("Notification Message Application : Notification Message : {}",order);	
		LOG.info("Notification Message Application : Message ID : {}",order.getMessageId());
		LOG.info("Notification Message Application : Message Details : {}",order.getMessageDetails());

		LOG.info("----------------------------------------------------");

		notificationMessageService.processNotificationMessage(order);
	}
	
}
