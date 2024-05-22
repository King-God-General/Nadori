package com.ssafy.nadori.model.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class Plan {
	private int planId;
	
	private Member writer;
	
	private String title;
	
	private String description;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
}
