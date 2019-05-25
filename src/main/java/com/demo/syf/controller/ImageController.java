package com.demo.syf.controller;

import java.awt.Image;
import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.syf.model.TokenBean;
import com.demo.syf.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Value("${api.url}")
	private String apiUrl;
	
	@Autowired
	ImageService imageService;
	
	@GetMapping
	public ResponseEntity<?> fetchAll(){
		return new ResponseEntity<>("b",HttpStatus.NOT_IMPLEMENTED);
	}
	
	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestBody MultipartFile file,Principal user){
		System.out.println(user.getName());
		byte[] fileBytes;
		try {
			fileBytes = file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try{
			imageService.uploadImage(fileBytes);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}


}
