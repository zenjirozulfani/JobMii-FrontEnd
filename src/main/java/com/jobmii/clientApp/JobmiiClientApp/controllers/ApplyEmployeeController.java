package com.jobmii.clientApp.JobmiiClientApp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jobmii.clientApp.JobmiiClientApp.models.ApplyEmployee;
import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.History;
import com.jobmii.clientApp.JobmiiClientApp.services.ApplyEmployeeService;
import com.jobmii.clientApp.JobmiiClientApp.services.EmployeeService;
import com.jobmii.clientApp.JobmiiClientApp.services.HistoryService;

import lombok.AllArgsConstructor;

@Controller
// @RequestMapping("/apply")
@AllArgsConstructor
public class ApplyEmployeeController {
	private ApplyEmployeeService applyEmployeeService;
	private EmployeeService employeeService;
	private HistoryService historyService;

	@GetMapping("/apply")
	public String applyEmployeePage() {
		return "apply_employee/list";
	}

	@GetMapping("/history")
	public String historyPage() {
		return "apply_employee/history";
	}

	@GetMapping("/apply-employee")
	public String applyEmployeeIdPage(Model model) {
		Employee employee = employeeService.getUserByUsername();
		model.addAttribute("employee", employee);

		List<ApplyEmployee> applyEmployee = applyEmployeeService.getByEmployeeId();
		// ApplyEmployee applyEmployee = employeeService.getUserByUsername();
		model.addAttribute("applyEmployee", applyEmployee);

		return "apply_employee/list-employee";
	}

	@GetMapping("/apply-employee/{id}")
	public String HistoryApplyJobPage(@PathVariable Integer id, Model model) {
		Employee employee = employeeService.getUserByUsername();
		model.addAttribute("employee", employee);

		ApplyEmployee applyEmployee = applyEmployeeService.getAllById(id);
		model.addAttribute("applyEmployee", applyEmployee);

		List<History> history = historyService.getAllByApplyEmployee(id);
		model.addAttribute("history", history);

		return "history/history-employee";
	}

	@PostMapping("/apply-employee/create")
	public String create(ApplyEmployee applyEmployee) {
		applyEmployeeService.create(applyEmployee);
		return "redirect:/vacancy";
	}

	@PutMapping("/apply-employee/reject")
	public String update(ApplyEmployee applyEmployee) {
		Long id = applyEmployee.getId();
		applyEmployeeService.update(applyEmployee, id);
		return "redirect:/vacancy/client/" + applyEmployee.getVacancy().getId();
	}

	@PutMapping("/apply-employee/accept")
	public String updateAcc(ApplyEmployee applyEmployee) {
		Long id = applyEmployee.getId();
		applyEmployeeService.update(applyEmployee, id);

		Employee employee = employeeService.getUserById(applyEmployee.getEmployee().getId());
		employee.setStatus(true);

		employeeService.updateStatus(employee.getId(), employee);
		return "redirect:/vacancy/client/" + applyEmployee.getVacancy().getId();
	}

}
