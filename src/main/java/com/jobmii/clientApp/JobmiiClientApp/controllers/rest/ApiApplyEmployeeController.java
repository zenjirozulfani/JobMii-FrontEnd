package com.jobmii.clientApp.JobmiiClientApp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobmii.clientApp.JobmiiClientApp.models.ApplyEmployee;
import com.jobmii.clientApp.JobmiiClientApp.services.ApplyEmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class ApiApplyEmployeeController {
	private ApplyEmployeeService applyEmployeeService;

	@GetMapping("/api/apply-employee")
	public List<ApplyEmployee> getAll() {
		return applyEmployeeService.getAll();
	}

	@GetMapping("/api/apply-employee-id")
	public List<ApplyEmployee> getByEmployeId() {
		return applyEmployeeService.getByEmployeeId();
	}

}
