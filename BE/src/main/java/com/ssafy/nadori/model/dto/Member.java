package com.ssafy.nadori.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	private String memberId;
	private String nickname;
	private String username;
}