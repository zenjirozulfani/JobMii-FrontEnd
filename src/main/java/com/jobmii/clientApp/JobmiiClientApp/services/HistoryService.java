package com.jobmii.clientApp.JobmiiClientApp.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobmii.clientApp.JobmiiClientApp.models.History;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import lombok.NoArgsConstructor;

@Service
public class HistoryService {
	private RestTemplate restTemplate;

	@Autowired
	public HistoryService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${server.baseUrl}")
	private String url;

	public List<History> getAll() {
		return restTemplate.exchange(
				url + "/history",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<History>>() {
				}).getBody();
	}

	public List<History> getAllByApplyEmployee(int id) {
		return restTemplate.exchange(
				url + "/history/apply-job/" + id,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<History>>() {
				}).getBody();
	}

}
