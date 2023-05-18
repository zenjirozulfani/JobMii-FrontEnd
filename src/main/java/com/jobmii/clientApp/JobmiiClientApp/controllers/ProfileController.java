package com.jobmii.clientApp.JobmiiClientApp.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jobmii.clientApp.JobmiiClientApp.models.Client;
import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.EmployeeRequest;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.UpdateEmployeeRequest;
import com.jobmii.clientApp.JobmiiClientApp.services.EmployeeService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {
	private EmployeeService employeeService;

	@GetMapping
	public String vacancyPage(Model model) {
		// Employee employee = employeeService.getUserById(id);
		// model.addAttribute("employee", employee);

		Employee employee = employeeService.getUserByUsername();
		model.addAttribute("employee", employee);

		Client client = employeeService.getUserForClient();
		model.addAttribute("client", client);

		UpdateEmployeeRequest updateEmployeeRequest = new UpdateEmployeeRequest();
		model.addAttribute("updateEmployeeRequest", updateEmployeeRequest);
		return "profile/index";
	}

	@PutMapping("/update-client")
	public String updateClient(Client client) throws IOException {
		Client clientNew = employeeService.getUserForClient();

		employeeService.updateClient(client, clientNew.getId());
		return "redirect:/profile";
	}

	@PutMapping("/update")
	public String update(UpdateEmployeeRequest updateEmployeeRequest) throws IOException {
		Employee employee = employeeService.getUserByUsername();

		String uploadDir = "src/main/resources/static/pdf";
		String fileName = employeeService.saveCv(uploadDir, updateEmployeeRequest);
		updateEmployeeRequest.setCv(fileName);

		employeeService.updateEmployee(updateEmployeeRequest, employee.getId());
		return "redirect:/profile";
	}
}
