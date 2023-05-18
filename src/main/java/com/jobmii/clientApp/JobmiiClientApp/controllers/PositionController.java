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
import com.jobmii.clientApp.JobmiiClientApp.models.Position;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.ClientRequest;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.EmployeeRequest;
import com.jobmii.clientApp.JobmiiClientApp.services.ClientService;
import com.jobmii.clientApp.JobmiiClientApp.services.EmployeeService;
import com.jobmii.clientApp.JobmiiClientApp.services.PositionService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/position")
public class PositionController {
	private PositionService positionService;

	@GetMapping
	public String index(Model model, Position position) {
		List<Position> positions = positionService.getAll();
		model.addAttribute("positions", positions);
		
		Position newPosition = new Position();
		model.addAttribute("newPosition", newPosition);
		return "position/index";
	}

	@PostMapping
	public String create(Position position) {
		positionService.create(position);
		return "redirect:/position";
	}
}