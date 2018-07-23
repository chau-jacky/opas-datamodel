package com.opas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opas.service.NotificationMessageService;

@Controller
public class NotificationMessageController {

	@Autowired
	private NotificationMessageService notificationMessageService;

	@RequestMapping("/notificationMessage")
	public String list(Model model) {
		model.addAttribute("notificationMessage", notificationMessageService.getAllNotificationMessagesList());
		return "notificationMessage";
	}

}
