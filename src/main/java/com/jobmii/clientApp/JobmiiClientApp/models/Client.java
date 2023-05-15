package com.jobmii.clientApp.JobmiiClientApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	private long id;
	private String name;
	private String email;
	private String phone;
	private String description;
}
