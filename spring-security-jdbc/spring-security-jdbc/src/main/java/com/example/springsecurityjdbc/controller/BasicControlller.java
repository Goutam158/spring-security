package com.example.springsecurityjdbc.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicControlller {
	
	private static Logger logger = LoggerFactory.getLogger(BasicControlller.class);
	
	
	
	@GetMapping(path = "/fetch")
	public String getReturn(Principal principal) {
		logger.info("inside default method .............................. with User : : " + principal.getName());
		return "<h1>Welcome Home </h1>";
	}
	
	@GetMapping(path = "/admin")
	public String getAdmin(Principal principal) {
		logger.info("************************ inside admin method .............................with User : : " + principal.getName());
		return "<h1>Welcome Home Admin</h1>";
	}
	
	@GetMapping(path = "/user")
	public String getUser(Principal principal) {
		logger.info("************************ inside user method .............................with User : : " + principal.getName());
		return "<h1>Welcome Home User </h1>";
	}

	
}
