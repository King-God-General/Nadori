package com.ssafy.nadori.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	
	public Member(String memberId, String nickname, String username) {
		this.memberId = memberId;
		this.nickname = nickname;
		this.username = username;
	}
	private String memberId;
	private String nickname;
	private String username;
}