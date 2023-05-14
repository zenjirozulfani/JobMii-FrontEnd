package com.jobmii.clientApp.JobmiiClientApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.LoginRequest;
import com.jobmii.clientApp.JobmiiClientApp.services.LoginService;

import lombok.AllArgsConstructor;

@Controller
// @RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
	private LoginService loginService;

	@GetMapping("/login")
	public String loginPage(LoginRequest loginRequest) {
		return "auth/login";
	}

	@GetMapping("/dashboard")
	public String dashboardPage() {
		return "/dashboard";
	}

	@PostMapping("/login")
	public String login(LoginRequest loginRequest) {
		if (!loginService.login(loginRequest)) {
			return "redirect:/login?error=true";
		}
		return "redirect:/dashboard";
	}

}
