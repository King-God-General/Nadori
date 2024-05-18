package com.ssafy.nadori.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Attraction {
	String attractionId;
	String overview;
	String cat1;
	String cat2;
	String cat3;
	String title;
	String addr1;
	String addr2;
	String zipcode;
	int contentTypeId;
	String firstImage;
	String firstImage2;
	int sidoCode;
	int gugunCode;
	float latitude; 
	float longitude;
}