package com.opas.configuration;

import java.util.Arrays;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessagingConfiguration {

	private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
	
	private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";
	private static final String OPAS_NOTIFICATION_QUEUE = "opas-notification-queue";
	private static final String OPAS_PUBLISH_TOPIC = "opas.publish.topic";

	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		connectionFactory.setTrustedPackages(Arrays.asList("com.opas"));
		return connectionFactory;
	}
	
	
	@Bean 
	public JmsTemplate jmsTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(ORDER_RESPONSE_QUEUE);
		return template;
	}

	/* For Point-to-Point */
	@Bean 
	public JmsTemplate jmsNotificationTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(OPAS_NOTIFICATION_QUEUE);
		return template;
	}

	/* For Pub-Sub */
	@Bean 
	public JmsTemplate jmsPubSubTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(OPAS_PUBLISH_TOPIC);
		template.setPubSubDomain(true);
		return template;
	}

	
}