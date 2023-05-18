package com.jobmii.clientApp.JobmiiClientApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobmii.clientApp.JobmiiClientApp.models.PositionVacancy;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.PositionVacancyRequest;

@Service
public class PositionVacancyService {
	private RestTemplate restTemplate;

	@Autowired
	public PositionVacancyService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${server.baseUrl}")
	private String url;

	public List<PositionVacancy> getAll() {
		return restTemplate.exchange(
				url + "/position-vacancy",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<PositionVacancy>>() {
				}).getBody();
	}

	public List<PositionVacancy> getByVacancy(long l) {
		return restTemplate.exchange(
				url + "/position-vacancy/" + l,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<PositionVacancy>>() {
				}).getBody();
	}

	public PositionVacancy create(PositionVacancyRequest positionVacancy) {
		return restTemplate.exchange(
				url + "/position-vacancy",
				HttpMethod.POST,
				new HttpEntity(positionVacancy),
				new ParameterizedTypeReference<PositionVacancy>() {
				}).getBody();
	}

}
