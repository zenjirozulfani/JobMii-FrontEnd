package com.jobmii.clientApp.JobmiiClientApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.LoginRequest;
import com.jobmii.clientApp.JobmiiClientApp.services.EmployeeService;
import com.jobmii.clientApp.JobmiiClientApp.services.LoginService;
import com.jobmii.clientApp.JobmiiClientApp.services.UserService;
import com.jobmii.clientApp.JobmiiClientApp.services.VacancyService;

import lombok.AllArgsConstructor;

@Controller
// @RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
	private LoginService loginService;
	private UserService userService;
	private EmployeeService employeeService;
	private VacancyService vacancyService;

	@GetMapping("/login")
	public String loginPage(LoginRequest loginRequest) {
		return "auth/login";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		Employee employee = employeeService.getUserByUsername();
		model.addAttribute("employee", employee);

		int countEmployee = userService.countEmployee();
		model.addAttribute("countEmployee", countEmployee);

		int countClient = userService.countClient();
		model.addAttribute("countClient", countClient);

		int countVacancy = vacancyService.countVacancy();
		model.addAttribute("countVacancy", countVacancy);

		int countJob = userService.countEmployeeJob();
		model.addAttribute("countJob", countJob);

		int countNoJob = userService.countEmployeeNoJob();
		model.addAttribute("countNoJob", countNoJob);

		int countMyVacancy = vacancyService.countMyVacancy(employee.getId());
		model.addAttribute("countMyVacancy", countMyVacancy);

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
