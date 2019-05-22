package com.demo.syf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping
	public ResponseEntity<?> fetchAll(){
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		return new ResponseEntity<>(e.encode("admin"),HttpStatus.NOT_IMPLEMENTED);
	}

}
