package com.jobmii.clientApp.JobmiiClientApp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.Position;
import com.jobmii.clientApp.JobmiiClientApp.services.EmployeeService;
import com.jobmii.clientApp.JobmiiClientApp.services.PositionService;

import lombok.AllArgsConstructor;

@Controller
// @RestController
@RequestMapping("/mapping")
@AllArgsConstructor
// @PreAuthorize("hasRole('HR')")
public class MappingController {
	private EmployeeService employeeService;
	private PositionService positionService;

	// @PreAuthorize("hasAuthority('READ_HR')")
	@GetMapping
	public String home(Model model) {
		List<Employee> employees = employeeService.getAllCV();
		model.addAttribute("employees", employees);

		// model.addAttribute("upRegion", regionService.getById(id));
		// Employee newEmployee = new Employee();
		// model.addAttribute("newEmployee", newEmployee);

		return "mapping/index";
	}

	@GetMapping("/employee/{id}")
	public String mapping(@PathVariable Integer id, Model model) {
		List<Employee> employees = employeeService.getAllCV();
		model.addAttribute("employees", employees);

		Employee employeeData = employeeService.getUserById(id);
		model.addAttribute("employeeData", employeeData);

		List<Position> posisi = positionService.getAll();
		model.addAttribute("posisi", posisi);

		// model.addAttribute("upRegion", regionService.getById(id));
		// Employee newEmployee = new Employee();
		// model.addAttribute("newEmployee", newEmployee);

		return "mapping/detail-cv";
	}

	@PutMapping("/employee")
	public String updateMapping(Employee employee) {
		Long id = employee.getId();
		employeeService.updateMapping(id, employee);
		return "redirect:/mapping";
	}

	@PutMapping("/employee-reject")
	public String updateMappingReject(Employee employee) {
		Long id = employee.getId();
		employeeService.rejectMapping(id, employee);
		return "redirect:/mapping";
	}
}
