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
	private String username;
}