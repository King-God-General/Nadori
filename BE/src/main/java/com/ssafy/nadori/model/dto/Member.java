package com.ssafy.nadori.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	@NonNull
	String email; //email("sample@sample.com")
	
	@NonNull
	String password;
	
	@NonNull
	String nickname; //nickname("홍길동")
	
	String gender; //gender("female", "male")
	
	String birthdate; //birthyear(2002)+birthday(1130)
}