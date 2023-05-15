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

import com.jobmii.clientApp.JobmiiClientApp.models.Client;
import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.ClientRequest;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.EmployeeRequest;
import com.jobmii.clientApp.JobmiiClientApp.services.ClientService;
import com.jobmii.clientApp.JobmiiClientApp.services.EmployeeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/mitra")
public class ClientController {
	private ClientService clientService;

	// @PreAuthorize("hasAuthority('READ_HR')")
	@GetMapping
	public String home(ClientRequest clientRequest, Model model) {
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients", clients);

		// model.addAttribute("upRegion", regionService.getById(id));
		Client newClient = new Client();
		model.addAttribute("newClient", newClient);

		return "mitra/index";
	}

	@PostMapping
	public String create(ClientRequest clientRequest) {
		clientService.create(clientRequest);
		return "redirect:/mitra";
	}
}
