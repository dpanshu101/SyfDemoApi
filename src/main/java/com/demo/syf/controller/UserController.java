package com.demo.syf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.syf.entities.User;
import com.demo.syf.service.UserService;

@RestController("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<?> fetchAll() {
		return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody User user) {
		try {
			userService.addNewUser(user);
		} catch (Exception e) {
			logger.error("error in user controller",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
