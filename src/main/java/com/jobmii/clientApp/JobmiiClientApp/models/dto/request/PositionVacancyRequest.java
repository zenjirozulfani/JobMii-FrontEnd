package com.jobmii.clientApp.JobmiiClientApp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionVacancyRequest {
	private Integer positionId;
	private Integer vacancyId;
	private int quota;
}
