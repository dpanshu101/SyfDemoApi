package com.demo.syf;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import javax.persistence.Entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.demo.syf.entities.Image;
import com.demo.syf.model.Response;
import com.demo.syf.model.TokenBean;
import com.demo.syf.repositories.ImageRepo;
import com.demo.syf.service.ImageService;

@RunWith(SpringRunner.class)
public class ImageServiceTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public ImageService imageService() {
			return new ImageService();
		}

		@Bean
		public TokenBean tokenBean() {
			return new TokenBean();
		}
	}

	@Autowired
	ImageService imageService;

	@Autowired
	TokenBean tokenBean;

	@MockBean
	RestTemplate restTemplate;

	@MockBean
	ImageRepo imageRepo;

	@Before
	public void setup() {
		Mockito.when(restTemplate.postForObject("test", "test", Response.class)).thenReturn(new Response());
		tokenBean.setAccess_token("test");
		// Mockito.when(imageService.getToken()).thenReturn(tokenBean);
		ReflectionTestUtils.setField(imageService, "authUrl", "http://test");
		ReflectionTestUtils.setField(imageService, "apiUrl", "http://test");

		Image img = new Image();
		Mockito.when(imageRepo.save(img)).thenReturn(img);
	}

	@Test(expected = ResourceAccessException.class)
	public void validateUpload() {
		byte[] fileBytes = "test".getBytes();
		Response res = imageService.uploadImage(fileBytes, "test user");
	}

}
