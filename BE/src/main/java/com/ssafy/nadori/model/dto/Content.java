package com.ssafy.nadori.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Content {
	private int contentId;
	private int planId;
	private int dayNum;
	private int contentNum;
	private String memo;
	private Attraction attraction;
}
