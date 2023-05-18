package com.jobmii.clientApp.JobmiiClientApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {
	private long id;
	private String title;
	private String address;
	private String start_date;
	private String end_date;
	private boolean isActive = true;
	private Employee mitra;
}
