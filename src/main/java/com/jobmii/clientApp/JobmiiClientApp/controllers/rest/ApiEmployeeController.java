package com.jobmii.clientApp.JobmiiClientApp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.services.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class ApiEmployeeController {
	private EmployeeService employeeService;

	@GetMapping
	public List<Employee> getAll() {
		return employeeService.getAll();
	}
}
