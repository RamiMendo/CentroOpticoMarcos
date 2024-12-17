package com.opticamarcos.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class MenuController {

	@GetMapping	
	public String getHomeMenu() {
		return "menu/index";
	}
	
}
 