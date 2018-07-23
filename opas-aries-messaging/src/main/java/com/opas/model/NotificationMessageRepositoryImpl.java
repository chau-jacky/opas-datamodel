package com.opas.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;


@Service("notificationMessageRepository")
public class NotificationMessageRepositoryImpl implements NotificationMessageRepository{

	private final Map<Integer, NotificationMessage> notificationMessages = new ConcurrentHashMap<Integer, NotificationMessage>();
	
	@Override
	public void putNotificationMessage(NotificationMessage message) {
		notificationMessages.put(new Integer(message.getMessageId()), message);
	}

	@Override
	public NotificationMessage getNotificationMessage(int messageId) {
		return notificationMessages.get(messageId);
	}

	public Map<Integer, NotificationMessage> getAllNotificationMessages(){
		return notificationMessages;
	}
}
