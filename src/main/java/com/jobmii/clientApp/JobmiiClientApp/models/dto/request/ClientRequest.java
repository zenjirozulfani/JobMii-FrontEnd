package com.jobmii.clientApp.JobmiiClientApp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
	private String name;
	private String email;
	private String phone;
	private String description;
	private String username;
	private String password;
}
