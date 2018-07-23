package com.opas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opas.service.CounterpartyService;

@Controller
@RequestMapping("opas")
public class CounterpartyController {

	@Autowired
	private CounterpartyService counterpartyService;

	@RequestMapping("/counterparties")
	public String list(Model model) {
		model.addAttribute("counterparties", counterpartyService.getAllCounterparties());
		return "counterparties";
	}

}
