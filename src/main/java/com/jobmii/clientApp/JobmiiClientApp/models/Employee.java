package com.jobmii.clientApp.JobmiiClientApp.models;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private long id;
	private String name;
	private String email;
	private String phone;
	private String cv;
	private Boolean status;
	private User user;
	private Position position;
}
