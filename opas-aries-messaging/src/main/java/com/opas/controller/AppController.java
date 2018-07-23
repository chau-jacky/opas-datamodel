package com.opas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.opas.model.NotificationMessage;
import com.opas.service.NotificationMessageService;

import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Controller
public class AppController {

	static final Logger LOG = LoggerFactory.getLogger(AppController.class);

	@Autowired
	NotificationMessageService notificationMessageService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String prepareProduct(ModelMap model) {
		return "index";
	}

	@RequestMapping(value = { "/notification" }, method = RequestMethod.GET)
	public String prepareOrder(ModelMap model) {
		LOG.info("new Notification-GET is being triggered");

		NotificationMessage message = new NotificationMessage();
		model.addAttribute("notificationMessage", message);
		return "notification";
	}
	
	@RequestMapping(value = { "/notification" }, method = RequestMethod.POST)
	public String sendOrder(@Valid NotificationMessage message, BindingResult result, ModelMap model) {
		LOG.info("new Notification-POST is being triggered");

		if (result.hasErrors()) {
			return "notification";
		}
		notificationMessageService.sendNotificationMessage(message);
		model.addAttribute("success", "Message Details : " + message.getMessageDetails() + " created.");
		return "notification";
	}


}
