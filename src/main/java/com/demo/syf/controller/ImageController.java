package com.demo.syf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {
	
	@GetMapping
	public ResponseEntity<?> fetchAll(){
		return new ResponseEntity<>("b",HttpStatus.NOT_IMPLEMENTED);
	}


}
