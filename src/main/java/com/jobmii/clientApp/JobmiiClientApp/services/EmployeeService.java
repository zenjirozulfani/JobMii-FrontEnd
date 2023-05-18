package com.jobmii.clientApp.JobmiiClientApp.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.jobmii.clientApp.JobmiiClientApp.models.Client;
import com.jobmii.clientApp.JobmiiClientApp.models.Employee;
import com.jobmii.clientApp.JobmiiClientApp.models.User;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.EmployeeRequest;
import com.jobmii.clientApp.JobmiiClientApp.models.dto.request.UpdateEmployeeRequest;
import com.jobmii.clientApp.util.BasicHeader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class EmployeeService {
	private final String UPLOAD_DIR = "D:/MCC/Segmen4/Jobmii-ClientApp/src/main/resources/templates/pdf";
	private RestTemplate restTemplate;

	@Autowired
	public EmployeeService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${server.baseUrl}")
	private String url;

	public List<Employee> getAll() {
		return restTemplate.exchange(
				url + "/employee-mii",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<Employee>>() {
				}).getBody();
	}

	public List<Employee> getAllHr() {
		return restTemplate.exchange(
				url + "/employee-hr",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<Employee>>() {
				}).getBody();
	}

	public List<Employee> getAllCV() {
		return restTemplate.exchange(
				url + "/employee-cv",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<Employee>>() {
				}).getBody();
	}

	public List<Employee> getForApplyEmployee() {
		return restTemplate.exchange(
				url + "/employee-free",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<Employee>>() {
				}).getBody();
	}

	public Employee getUserByUsername() {
		return restTemplate.exchange(
				url + "/employee-login",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Employee>() {
				}).getBody();
	}

	public Client getUserForClient() {
		return restTemplate.exchange(
				url + "/employee-login",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Client>() {
				}).getBody();
	}

	public Employee getUserById(long l) {
		return restTemplate.exchange(
				url + "/employee/" + l,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Employee>() {
				}).getBody();
	}

	public Employee updateMapping(Long id, Employee employee) {
		return restTemplate.exchange(
				url + "/update-mapping/" + id,
				HttpMethod.PUT,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				new HttpEntity(employee),
				new ParameterizedTypeReference<Employee>() {
				}).getBody();
	}

	public Employee updateStatus(Long id, Employee employee) {
		return restTemplate.exchange(
				url + "/employee/" + id,
				HttpMethod.PUT,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				new HttpEntity(employee),
				new ParameterizedTypeReference<Employee>() {
				}).getBody();
	}

	public Employee rejectMapping(Long id, Employee employee) {
		return restTemplate.exchange(
				url + "/reject-mapping/" + id,
				HttpMethod.PUT,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				new HttpEntity(employee),
				new ParameterizedTypeReference<Employee>() {
				}).getBody();
	}

	public UpdateEmployeeRequest updateEmployee(UpdateEmployeeRequest updateEmployeeRequest, long id) {
		return restTemplate.exchange(
				url + "/update-employee/" + id,
				HttpMethod.PUT,
				new HttpEntity(updateEmployeeRequest),
				new ParameterizedTypeReference<UpdateEmployeeRequest>() {
				}).getBody();
	}

	public Client updateClient(Client client, long id) {
		return restTemplate.exchange(
				url + "/update-employee/" + id,
				HttpMethod.PUT,
				new HttpEntity(client),
				new ParameterizedTypeReference<Client>() {
				}).getBody();
	}

	public EmployeeRequest registerHr(EmployeeRequest employeeRequest) {
		return restTemplate.exchange(
				url + "/register-hr",
				HttpMethod.POST,
				new HttpEntity(employeeRequest),
				new ParameterizedTypeReference<EmployeeRequest>() {
				}).getBody();
	}

	public EmployeeRequest register(EmployeeRequest employeeRequest) {
		return restTemplate.exchange(
				url + "/register-employee",
				HttpMethod.POST,
				new HttpEntity(employeeRequest),
				new ParameterizedTypeReference<EmployeeRequest>() {
				}).getBody();
	}

	public String saveCv(String uploadDir, UpdateEmployeeRequest employee) throws IOException {
		MultipartFile cvUpload = employee.getUploadCv();
		System.out.println(cvUpload);
		if (cvUpload == null || cvUpload.isEmpty()) {
			return null; // return null if image is empty or null
		}

		String fileName = StringUtils.cleanPath(employee.getUploadCv().getOriginalFilename());
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = employee.getUploadCv().getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException e) {
			throw new IOException("Could not save image file: " + fileName, e);
		}
	}

	// public User checkUsername(String username) {
	// return restTemplate.exchange(
	// url + "/user/check-username/" + username,
	// HttpMethod.GET,
	// // new HttpEntity(BasicHeader.createBasicHeader()),
	// null,
	// new ParameterizedTypeReference<User>() {
	// }).getBody();
	// }

	public Map<String, Boolean> checkUsername(String username) {
		ResponseEntity<Map<String, Boolean>> response = restTemplate.exchange(
				url + "/user/check-username/" + username,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Map<String, Boolean>>() {
				});
		return response.getBody();
	}

	public Map<String, Boolean> checkEmail(String email) {
		ResponseEntity<Map<String, Boolean>> response = restTemplate.exchange(
				url + "/user/check-email/" + email,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Map<String, Boolean>>() {
				});
		return response.getBody();
	}

}
