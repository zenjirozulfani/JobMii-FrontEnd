package com.jobmii.clientApp.JobmiiClientApp.controllers.rest;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.User;
import com.jobmii.clientApp.JobmiiClientApp.services.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/mapping")
@AllArgsConstructor
public class ApiMappingController {
	private EmployeeService employeeService;

	@GetMapping
	public List<Employee> getAllCV() {
		return employeeService.getAllCV();
	}

	@GetMapping("/username/{username}")
	public Map<String, Boolean> getByUsername(@PathVariable String username) {
		return employeeService.checkUsername(username);
	}

	@GetMapping("/email/{email}")
	public Map<String, Boolean> getByEmail(@PathVariable String email) {
		return employeeService.checkEmail(email);
	}
}
