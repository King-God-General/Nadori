package com.ssafy.nadori.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Content {

	@NonNull
	private int contentId;
	
	@NonNull
	private int dayNum;

	@NonNull
	private int order;
	
	private String memo;
	
	@NonNull
	private Attraction attraction;
}
