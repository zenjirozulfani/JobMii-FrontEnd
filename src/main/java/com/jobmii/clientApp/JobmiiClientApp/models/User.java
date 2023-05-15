package com.jobmii.clientApp.JobmiiClientApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private long id;
	private String username;
	private String password;
	private Boolean isEnabled = true;
	private Boolean isAccountNonLocked = true;
}
