package com.jobmii.clientApp.JobmiiClientApp.controllers.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobmii.clientApp.JobmiiClientApp.models.PositionVacancy;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.PositionVacancyRequest;
import com.jobmii.clientApp.JobmiiClientApp.services.PositionVacancyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/position-vacancy")
@AllArgsConstructor
public class ApiPositionVacancyController {
	private PositionVacancyService positionVacancyService;

	@PostMapping
	public PositionVacancy create(@RequestBody PositionVacancyRequest positionVacancy) {
		return positionVacancyService.create(positionVacancy);
	}
}
