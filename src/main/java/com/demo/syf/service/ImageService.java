package com.demo.syf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

	public Response uploadImage(byte[] fileBytes,String user) {
		getToken();
		RestTemplate t = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + tokenBean.getAccess_token());

		HttpEntity<?> requestEnty = new HttpEntity<>(fileBytes, headers);

		Response resp = t.postForObject(apiUrl, requestEnty, Response.class);

		try{
			saveUploadToDb(resp,user);
		}catch (Exception e) {
			e.printStackTrace();
		}

		return resp;

	}

	private void saveUploadToDb(Response resp,String user) {
		Image img = new Image();
		img.setDelete_hash(resp.getData().getDeletehash());
		img.setUrl(resp.getData().getLink());
		img.setUsername(user);

	}

}
