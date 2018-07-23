package com.opas.messaging;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class StringMessageReceiver {

	static final Logger LOG = LoggerFactory.getLogger(StringMessageReceiver.class);
	private static final String ORDER_QUEUE = "opas-string-queue";
	
	@JmsListener(destination = ORDER_QUEUE)
	public void receiveMessage(final Message<String> message) throws JMSException {
		LOG.info("----------------------------------------------------");
		MessageHeaders headers =  message.getHeaders();
		LOG.info("String Message - Application : headers received : {}", headers);
		
		String order = message.getPayload();
		LOG.info("String Message - Application : message : {}",order);	

		LOG.info("----------------------------------------------------");

	}
	
}
