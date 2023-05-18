package com.jobmii.clientApp.JobmiiClientApp.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.ByteArrayResource;
import com.jobmii.clientApp.JobmiiClientApp.models.ApplyEmployee;
import com.jobmii.clientApp.JobmiiClientApp.models.Client;
import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.Position;
import com.jobmii.clientApp.JobmiiClientApp.models.PositionVacancy;
import com.jobmii.clientApp.JobmiiClientApp.models.Vacancy;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.PositionVacancyRequest;
import com.jobmii.clientApp.JobmiiClientApp.services.ApplyEmployeeService;
import com.jobmii.clientApp.JobmiiClientApp.services.EmployeeService;
import com.jobmii.clientApp.JobmiiClientApp.services.PositionService;
import com.jobmii.clientApp.JobmiiClientApp.services.PositionVacancyService;
import com.jobmii.clientApp.JobmiiClientApp.services.VacancyService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/vacancy")
@AllArgsConstructor
public class VacancyController {
	private ApplyEmployeeService applyEmployeeService;
	private EmployeeService employeeService;
	private VacancyService vacancyService;
	private PositionService positionService;
	private PositionVacancyService positionVacancyService;

	@GetMapping
	public String vacancyPage(Model model) {
		List<Vacancy> vacancies = vacancyService.getAll();
		model.addAttribute("vacancies", vacancies);

		List<PositionVacancy> positionVacancy = positionVacancyService.getAll();
		model.addAttribute("positionVacancy", positionVacancy);

		return "vacancy/index";
	}

	@GetMapping("my-vacancy")
	public String myVacancyPage(Model model) {
		Client client = employeeService.getUserForClient();
		model.addAttribute("client", client);

		List<Vacancy> vacancies = vacancyService.getAllMyVacancy(client.getId());
		model.addAttribute("vacancies", vacancies);

		return "vacancy/my-vacancy";
	}

	@GetMapping("/{id}")
	public String detailVacancy(@PathVariable Integer id, Model model) {
		Vacancy vacancy = vacancyService.getById(id);
		model.addAttribute("vacancy", vacancy);

		List<PositionVacancy> positionVacancy = positionVacancyService.getByVacancy(vacancy.getId());
		model.addAttribute("positionVacancy", positionVacancy);

		List<Employee> employeeFree = employeeService.getForApplyEmployee();
		model.addAttribute("employeeFree", employeeFree);

		Employee login = employeeService.getUserByUsername();
		model.addAttribute("login", login);

		List<ApplyEmployee> jobEmployee = applyEmployeeService.getJobApplicant(id);
		model.addAttribute("jobEmployee", jobEmployee);

		return "vacancy/detail";
	}

	@GetMapping("/client/{id}")
	public String detailVacancyClient(@PathVariable Integer id, Model model) {
		Client client = employeeService.getUserForClient();
		model.addAttribute("client", client);

		Vacancy vacancy = vacancyService.getById(id);
		model.addAttribute("vacancy", vacancy);

		List<PositionVacancy> positionVacancy = positionVacancyService.getByVacancy(vacancy.getId());
		model.addAttribute("positionVacancy", positionVacancy);

		List<Position> position = positionService.getAll();
		model.addAttribute("position", position);

		List<ApplyEmployee> apBelum = applyEmployeeService.getJobApply(id);
		model.addAttribute("apBelum", apBelum);

		List<ApplyEmployee> apSudah = applyEmployeeService.getDataAcc(id);
		model.addAttribute("apSudah", apSudah);

		String link = "http://localhost:8088/export/" + vacancy.getId();
		// Workbook link = applyEmployeeService.getEmployeeData(vacancy.getId());
		model.addAttribute("link", link);

		return "vacancy/detail-vacancy";
	}

	@GetMapping("/export/{vacancyId}")
	@ResponseBody
	public ResponseEntity<byte[]> exportEmployeeData(@PathVariable Integer vacancyId) {
		Workbook workbook = applyEmployeeService.getEmployeeDataWorkbook(vacancyId);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] excelData = outputStream.toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", "employee_data.xlsx");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		return ResponseEntity.ok()
				.headers(headers)
				.body(excelData);
	}

	@PostMapping("/position-vacancy")
	public String createPositionVacancy(PositionVacancyRequest positionVacancy) {
		positionVacancyService.create(positionVacancy);
		return "redirect:/vacancy/client/" + positionVacancy.getVacancyId();
	}

	@PostMapping
	public String create(Vacancy vacancy) {
		vacancyService.create(vacancy);
		return "redirect:/vacancy/my-vacancy";
	}
}
