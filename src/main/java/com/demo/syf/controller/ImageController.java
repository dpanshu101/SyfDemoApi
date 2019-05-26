package com.demo.syf.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.syf.entities.Image;
import com.demo.syf.model.Response;
import com.demo.syf.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Value("${api.url}")
	private String apiUrl;
	
	@Autowired
	ImageService imageService;
	
	@GetMapping
	public ResponseEntity<?> fetchAll(Principal user){
		List<Image> images=null;
		try {
			images=imageService.fetchImageByUser(user.getName());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(images==null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(images,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestBody MultipartFile file,Principal user){
		byte[] fileBytes;
		try {
			fileBytes = file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Response res;
		try{
			res= imageService.uploadImage(fileBytes,user.getName());
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}if(res==null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteImage(@RequestParam String deleteHash){
		try {
			imageService.deleteImage(deleteHash);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}


}
