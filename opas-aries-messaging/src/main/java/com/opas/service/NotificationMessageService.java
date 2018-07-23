package com.opas.service;

import java.util.List;
import java.util.Map;

import com.opas.model.NotificationMessage;

public interface NotificationMessageService {
 
    public void sendNotificationMessage(NotificationMessage message);
    
	public Map<Integer, NotificationMessage> getAllNotificationMessages();
	
	public List<NotificationMessage> getAllNotificationMessagesList();
	
	public void processNotificationMessage(NotificationMessage message);

}
