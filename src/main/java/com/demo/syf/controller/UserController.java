package com.demo.syf.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.syf.entities.User;
import com.demo.syf.service.UserService;

@RestController("/user")
public class UserController {

	// Logger logger = Logger

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<?> fetchAll() {
		return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody User user) {
		boolean saved;
		try {
			saved = userService.addNewUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
