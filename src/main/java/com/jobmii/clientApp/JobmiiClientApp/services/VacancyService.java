package com.jobmii.clientApp.JobmiiClientApp.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobmii.clientApp.JobmiiClientApp.models.Vacancy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class VacancyService {
	private RestTemplate restTemplate;

	@Autowired
	public VacancyService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${server.baseUrl}")
	private String url;

	public List<Vacancy> getAll() {
		return restTemplate.exchange(
				url + "/vacancy",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<Vacancy>>() {
				}).getBody();
	}

	public List<Vacancy> getAllMyVacancy(Long id) {
		return restTemplate.exchange(
				url + "/vacancy/my-vacancy/" + id,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<Vacancy>>() {
				}).getBody();
	}

	public int countVacancy() {
		return restTemplate.exchange(
				url + "/vacancy/count-vacancy",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Integer>() {
				}).getBody();
	}

	public int countMyVacancy(long l) {
		return restTemplate.exchange(
				url + "/vacancy/count-myvacancy/" + l,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Integer>() {
				}).getBody();
	}

	public Vacancy getById(int id) {
		return restTemplate.exchange(
				url + "/vacancy/" + id,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Vacancy>() {
				}).getBody();
	}

	public Vacancy create(Vacancy vacancy) {
		return restTemplate.exchange(
				url + "/vacancy/create",
				HttpMethod.POST,
				new HttpEntity(vacancy),
				new ParameterizedTypeReference<Vacancy>() {
				}).getBody();
	}
}
