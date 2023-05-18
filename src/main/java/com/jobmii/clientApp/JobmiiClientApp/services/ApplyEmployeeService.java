package com.jobmii.clientApp.JobmiiClientApp.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.jobmii.clientApp.JobmiiClientApp.models.ApplyEmployee;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;

@Service
public class ApplyEmployeeService {

	private RestTemplate restTemplate;

	@Autowired
	public ApplyEmployeeService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${server.baseUrl}")
	private String url;

	public ApplyEmployee getAllById(int id) {
		return restTemplate.exchange(
				url + "/apply-employee/" + id,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<ApplyEmployee>() {
				}).getBody();
	}

	public List<ApplyEmployee> getAll() {
		return restTemplate.exchange(
				url + "/apply-employee",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<ApplyEmployee>>() {
				}).getBody();
	}

	public List<ApplyEmployee> getByEmployeeId() {
		return restTemplate.exchange(
				url + "/apply-employee-id",
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<ApplyEmployee>>() {
				}).getBody();
	}

	public List<ApplyEmployee> getJobApplicant(int id) {
		return restTemplate.exchange(
				url + "/job-employee/" + id,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<ApplyEmployee>>() {
				}).getBody();
	}

	public List<ApplyEmployee> getJobApply(int id) {
		return restTemplate.exchange(
				url + "/job-client/" + id,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<ApplyEmployee>>() {
				}).getBody();
	}

	public Object getLink(long l) {
		return restTemplate.exchange(
				url + "/export/" + l,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<Object>() {
				}).getBody();
	}

	public Workbook getEmployeeDataWorkbook(Integer vacancyId) {
		String url = "http://localhost:8080/export/" + vacancyId;

		ResponseEntity<ByteArrayResource> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				ByteArrayResource.class);

		Workbook workbook = new XSSFWorkbook();
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(response.getBody().getByteArray())) {
			workbook = WorkbookFactory.create(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return workbook;
	}

	public ResponseEntity<ByteArrayResource> downloadEmployeeData(Integer vacancyId) {
		Workbook workbook = getEmployeeDataWorkbook(vacancyId);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", "employee_data.xlsx");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
			return ResponseEntity.ok()
					.headers(headers)
					.body(resource);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	public List<ApplyEmployee> getDataAcc(Integer id) {
		return restTemplate.exchange(
				url + "/data-accept/" + id,
				HttpMethod.GET,
				// new HttpEntity(BasicHeader.createBasicHeader()),
				null,
				new ParameterizedTypeReference<List<ApplyEmployee>>() {
				}).getBody();
	}

	public ApplyEmployee create(ApplyEmployee applyEmployee) {
		return restTemplate.exchange(
				url + "/apply-employee/create",
				HttpMethod.POST,
				new HttpEntity(applyEmployee),
				new ParameterizedTypeReference<ApplyEmployee>() {
				}).getBody();
	}

	public ApplyEmployee update(ApplyEmployee applyEmployee, long id) {
		return restTemplate.exchange(
				url + "/apply-employee/" + id,
				HttpMethod.PUT,
				new HttpEntity(applyEmployee),
				new ParameterizedTypeReference<ApplyEmployee>() {
				}).getBody();
	}
}
