package com.jobmii.clientApp.JobmiiClientApp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobmii.clientApp.JobmiiClientApp.models.History;
import com.jobmii.clientApp.JobmiiClientApp.services.HistoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/history")
@AllArgsConstructor
public class ApiHistoryController {
	private HistoryService historyService;

	@GetMapping
	public List<History> getAll() {
		return historyService.getAll();
	}

}
