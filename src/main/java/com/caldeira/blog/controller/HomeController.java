package com.caldeira.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String home() {
		return "Hello, this is the Blogging REST API. Go to /swagger-ui.html to know the endpoints of the application";
	}
	
}
