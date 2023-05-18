package com.jobmii.clientApp.JobmiiClientApp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.EmployeeRequest;
import com.jobmii.clientApp.JobmiiClientApp.services.EmployeeService;

import lombok.AllArgsConstructor;

@Controller
// @RestController
// @RequestMapping("/employee")
@AllArgsConstructor
// @PreAuthorize("hasRole('HR')")
public class EmployeeController {
	private EmployeeService employeeService;

	// @PreAuthorize("hasAuthority('READ_HR')")
	@GetMapping("/list-employee")
	public String home(Model model) {
		List<Employee> employees = employeeService.getAll();
		model.addAttribute("employees", employees);

		// model.addAttribute("upRegion", regionService.getById(id));
		// Employee newEmployee = new Employee();
		// model.addAttribute("newEmployee", newEmployee);

		return "employee/index";
	}

	@GetMapping("/employee/create")
	public String createPage(EmployeeRequest employeeRequest) {
		return "employee/create";
	}

	@GetMapping("/list-hr")
	public String listHr(EmployeeRequest employeeRequest) {
		return "hr/index";
	}

	@PostMapping("/register-hr")
	public String registerHr(EmployeeRequest employeeRequest) {
		employeeService.registerHr(employeeRequest);
		return "redirect:/list-hr";
	}

	@PostMapping("/register-employee")
	public String register(EmployeeRequest employeeRequest) {
		employeeService.register(employeeRequest);
		return "redirect:employee/create";
	}

}
