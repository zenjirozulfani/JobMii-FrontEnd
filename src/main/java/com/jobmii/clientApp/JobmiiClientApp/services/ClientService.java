package com.jobmii.clientApp.JobmiiClientApp.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobmii.clientApp.JobmiiClientApp.models.Client;
import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.User;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.ClientRequest;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.EmployeeRequest;
import com.jobmii.clientApp.util.BasicHeader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class ClientService {
	private RestTemplate restTemplate;

	@Autowired
	public ClientService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${server.baseUrl}")
	private String url;

	public List<Client> getAll() {
		return restTemplate.exchange(
				url + "/employee-client",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<Client>>() {
				}).getBody();
	}

	public Client create(ClientRequest clientRequest) {
		return restTemplate.exchange(
				url + "/register-client",
				HttpMethod.POST,
				new HttpEntity(clientRequest),
				new ParameterizedTypeReference<Client>() {
				}).getBody();
	}

}
