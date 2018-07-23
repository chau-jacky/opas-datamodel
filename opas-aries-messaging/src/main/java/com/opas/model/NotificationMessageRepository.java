package com.opas.model;

import java.util.Map;

public interface NotificationMessageRepository {

	public void putNotificationMessage(NotificationMessage message);
	
	public NotificationMessage getNotificationMessage(int messageId);
	
	public Map<Integer, NotificationMessage> getAllNotificationMessages();
}
