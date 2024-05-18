package com.ssafy.nadori.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	private int memberId;
	
	private String email; //email("sample@sample.com")
	
	private String password;
	
	private String username; //username("홍길동")
	
	private boolean gender; //gender("female", "male")
	private String birthdate; //birthyear(2002)+birthday(1130)
}