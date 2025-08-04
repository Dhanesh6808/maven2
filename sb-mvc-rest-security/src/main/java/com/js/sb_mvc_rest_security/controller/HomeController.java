package com.js.sb_mvc_rest_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/public")
	public String publicEndpoint() {
		return "This is the public Endpoint.";
	}
	@GetMapping("private")
	public String privateEndpoint() {
		return "This is a secured Endpoint.";
	}
	@GetMapping("/admin")
	public String adminEndpoint() {
		return "This is Admin Endpoint.";
	}
}
