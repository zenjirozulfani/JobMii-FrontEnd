package com.jobmii.clientApp.JobmiiClientApp.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

	private String username;
	private String email;
	private List<String> authorities;

}