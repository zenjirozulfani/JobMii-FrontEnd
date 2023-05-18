package com.jobmii.clientApp.JobmiiClientApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobmii.clientApp.JobmiiClientApp.models.User;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UserService {
	private RestTemplate restTemplate;

	@Autowired
	public UserService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${server.baseUrl}/user")
	private String url;

	public List<User> getAll() {
		return restTemplate.exchange(
				url,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<User>>() {
				}).getBody();
	}

	public int countEmployee() {
		return restTemplate.exchange(
				url + "/count-employee",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Integer>() {
				}).getBody();
	}

	public int countEmployeeJob() {
		return restTemplate.exchange(
				"http://localhost:8088/employee-job",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Integer>() {
				}).getBody();
	}

	public int countEmployeeNoJob() {
		return restTemplate.exchange(
				"http://localhost:8088/employee-nojob",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Integer>() {
				}).getBody();
	}

	public int countClient() {
		return restTemplate.exchange(
				url + "/count-client",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Integer>() {
				}).getBody();
	}

	public User getById(int id) {
		return restTemplate.exchange(
				url + "/" + id,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<User>() {
				}).getBody();
	}

	public User update(int id, User user) {
		return restTemplate.exchange(
				url + "/" + id,
				HttpMethod.PUT,
				// new HttpEntity(country, BasicHeader.createBasicHeader()),
				new HttpEntity(user),
				new ParameterizedTypeReference<User>() {
				}).getBody();
	}
}
