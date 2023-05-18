package com.jobmii.clientApp.JobmiiClientApp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobmii.clientApp.JobmiiClientApp.models.Position;
import com.jobmii.clientApp.JobmiiClientApp.services.PositionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/position")
@AllArgsConstructor
public class ApiPositionController {
	private PositionService positionService;

	@GetMapping
	public List<Position> getAll() {
		return positionService.getAll();
	}

	@PostMapping
	public Position create(@RequestBody Position position) {
		return positionService.create(position);
	}

}