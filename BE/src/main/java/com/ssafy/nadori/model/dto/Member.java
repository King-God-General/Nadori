package com.ssafy.nadori.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	
	public Member(String memberId, String nickname, String username, String accessToken, String refreshToken) {
		this.memberId = memberId;
		this.nickname = nickname;
		this.username = username;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	private String memberId;
	private String nickname;
	private String username;
	private String accessToken;
	private String refreshToken;
}