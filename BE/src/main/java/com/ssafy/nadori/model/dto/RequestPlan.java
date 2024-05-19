package com.ssafy.nadori.model.dto;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RequestPlan {
	private int planId;
	private int memberId;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
}
