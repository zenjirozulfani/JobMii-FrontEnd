package com.jobmii.clientApp.JobmiiClientApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyEmployee {
	private long id;
	private Employee employee;
	private User hr;
	private Vacancy vacancy;
	private String apply_date;
	private Status status;
	private String date_test;
	private String location_test;

}
