package com.jobmii.clientApp.JobmiiClientApp.models;

import com.jobmii.clientApp.JobmiiClientApp.models.key.PositionVacancyKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionVacancy {
	// private PositionVacancyKey positionVacancyKey;
	private Position position;
	private Vacancy vacancy;
	private int quota;
}
