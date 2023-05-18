package com.jobmii.clientApp.JobmiiClientApp.models.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
	private String name;
	private String email;
	private String phone;
	private String username;
	private String password;
	@JsonIgnore
	private MultipartFile uploadCv;
	private String cv;
	private String description;
}
