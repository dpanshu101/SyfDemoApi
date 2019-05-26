package com.demo.syf.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.demo.syf.entities.Image;
import com.demo.syf.model.Response;
import com.demo.syf.model.TokenBean;
import com.demo.syf.repositories.ImageRepo;

@Service
public class ImageService {
	Logger logger = LoggerFactory.getLogger(ImageService.class);

	@Value("${api.upload.url}")
	private String apiUrl;

	@Value("${api.auth.url}")
	private String authUrl;

	@Autowired
	TokenBean tokenBean;

	@Autowired
	ImageRepo imageRepo;

	public TokenBean getToken() {
		RestTemplate t = new RestTemplate();
		MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<>();

		parametersMap.add("refresh_token", "049b1558d40622faada818b34cc354057bd958ac");
		parametersMap.add("client_id", "9306a06fb8c855b");
		parametersMap.add("client_secret", "b5163749ef0856970f80d309a27cb346f52a0221");
		parametersMap.add("grant_type", "refresh_token");

		if (tokenBean.getAccess_token() == null) {
			tokenBean = t.postForObject(authUrl, parametersMap, TokenBean.class);
		}

		return tokenBean;
	}

	public Response uploadImage(byte[] fileBytes, String user) {
		getToken();
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + tokenBean.getAccess_token());

		HttpEntity<?> requestEnty = new HttpEntity<>(fileBytes, headers);

		Response resp = restTemplate.postForObject(apiUrl, requestEnty, Response.class);

		try {
			saveUploadToDb(resp, user);
		} catch (Exception e) {
			logger.error("error in image service", e);
		}

		return resp;

	}

	public List<Image> fetchImageByUser(String user) {
		return imageRepo.findByUsername(user);
	}

	private void saveUploadToDb(Response resp, String user) {
		Image img = new Image();
		img.setDelete_hash(resp.getData().getDeletehash());
		img.setUrl(resp.getData().getLink());
		img.setUsername(user);
		imageRepo.save(img);

	}

	public void deleteImage(String hash) {
		Image delImg = imageRepo.findByDeleteHash(hash);

		RestTemplate t = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Client-ID 9306a06fb8c855b");

		HttpEntity<?> requestEnty = new HttpEntity<>(headers);

		t.exchange(apiUrl + "/" + hash, HttpMethod.DELETE, requestEnty, String.class);

		if (delImg != null && delImg.getId() != null)
			imageRepo.deleteById(delImg.getId());

	}

}
