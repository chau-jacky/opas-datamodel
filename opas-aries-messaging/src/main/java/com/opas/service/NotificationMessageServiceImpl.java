package com.opas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opas.messaging.NotificationMessageSender;
import com.opas.model.NotificationMessage;
import com.opas.model.NotificationMessageRepository;

@Service("notifcationMessageService")
public class NotificationMessageServiceImpl implements NotificationMessageService {

	static final Logger LOG = LoggerFactory.getLogger(NotificationMessageServiceImpl.class);

	@Autowired
	NotificationMessageSender notificationMessageSender;

	@Autowired
	NotificationMessageRepository notificationMessageRepository;

	
	@Override
	public void sendNotificationMessage(NotificationMessage message) {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Application : sending order request {}", message);
		notificationMessageSender.sendMessage(message);
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	public Map<Integer, NotificationMessage> getAllNotificationMessages(){
		return notificationMessageRepository.getAllNotificationMessages();
	}

	
	@Override
	public void processNotificationMessage(NotificationMessage message) {
		notificationMessageRepository.putNotificationMessage(message);
		LOG.info("Notification Message Received {}", message.toString());
	}

	@Override
	public List<NotificationMessage> getAllNotificationMessagesList() {
		return new ArrayList<NotificationMessage>(notificationMessageRepository.getAllNotificationMessages().values());
	}

}