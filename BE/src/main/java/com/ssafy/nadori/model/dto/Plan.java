package com.ssafy.nadori.model.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
public class Plan {
	@NonNull
	private int planId;
	
	@NonNull
	private String title;
	
	private String description;
	
	@NonNull
	private LocalDate startDate;
	
	@NonNull
	private LocalDate endDate;
	
	private List<Content> contents;
}
