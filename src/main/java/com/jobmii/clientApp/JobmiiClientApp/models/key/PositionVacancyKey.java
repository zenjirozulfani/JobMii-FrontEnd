package com.jobmii.clientApp.JobmiiClientApp.models.key;

import com.jobmii.clientApp.JobmiiClientApp.models.Position;
import com.jobmii.clientApp.JobmiiClientApp.models.Vacancy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionVacancyKey {
	private Integer positionId;
	private Integer vacancyId;
}
