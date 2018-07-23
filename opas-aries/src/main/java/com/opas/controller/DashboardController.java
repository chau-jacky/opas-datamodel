package com.opas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("opas")
public class DashboardController {

	@RequestMapping("/dashboard")
	public String list(Model model) {
		return "dashboard";
	}

}
