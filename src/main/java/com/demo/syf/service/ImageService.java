package com.demo.syf.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.demo.syf.model.Response;
import com.demo.syf.model.TokenBean;

@Service
public class ImageService {

	@Value("${api.upload.url}")
	private String apiUrl;

	@Value("${api.auth.url}")
	private String authUrl;

	@Autowired
	TokenBean tokenBean;

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

	public void uploadImage(byte[] fileBytes) {
		getToken();
		RestTemplate t = new RestTemplate();

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		String fileString=new String(Base64.getEncoder().encode(fileBytes));
		body.add("file",fileString );

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + tokenBean.getAccess_token());

		HttpEntity<?> requestEnty = new HttpEntity<>(fileBytes, headers);

		Response resp = t.postForObject(apiUrl, requestEnty, Response.class);
		System.out.println(resp);

	}

}
