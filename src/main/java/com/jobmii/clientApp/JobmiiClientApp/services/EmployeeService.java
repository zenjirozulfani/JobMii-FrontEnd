package com.jobmii.clientApp.JobmiiClientApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.util.BasicHeader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

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
}
