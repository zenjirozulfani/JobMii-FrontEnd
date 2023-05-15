package com.jobmii.clientApp.JobmiiClientApp.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.User;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.EmployeeRequest;
import com.jobmii.clientApp.util.BasicHeader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class EmployeeService {
	private RestTemplate restTemplate;

	@Autowired
	public EmployeeService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${server.baseUrl}")
	private String url;

	public List<Employee> getAll() {
		return restTemplate.exchange(
				url + "/employee-mii",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<Employee>>() {
				}).getBody();
	}

	public EmployeeRequest register(EmployeeRequest employeeRequest) {
		return restTemplate.exchange(
				url + "/register-employee",
				HttpMethod.POST,
				new HttpEntity(employeeRequest),
				new ParameterizedTypeReference<EmployeeRequest>() {
				}).getBody();
	}

	// public User checkUsername(String username) {
	// return restTemplate.exchange(
	// url + "/user/check-username/" + username,
	// HttpMethod.GET,
	// // new HttpEntity(BasicHeader.createBasicHeader()),
	// null,
	// new ParameterizedTypeReference<User>() {
	// }).getBody();
	// }

	public Map<String, Boolean> checkUsername(String username) {
		ResponseEntity<Map<String, Boolean>> response = restTemplate.exchange(
				url + "/user/check-username/" + username,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Map<String, Boolean>>() {
				});
		return response.getBody();
	}

	public Map<String, Boolean> checkEmail(String email) {
		ResponseEntity<Map<String, Boolean>> response = restTemplate.exchange(
				url + "/user/check-email/" + email,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Map<String, Boolean>>() {
				});
		return response.getBody();
	}

}
