package com.ssafy.nadori.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nadori.model.dto.Member;
import com.ssafy.nadori.model.service.MemberService;
import com.ssafy.nadori.model.service.OAuthService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
	private final MemberService memberService;
	private final OAuthService oAuthService;
	
	@GetMapping("/login/kakao")
	public boolean login(@RequestParam(name = "code", required = false) String code){	
		String accessToken = oAuthService.getAccessToken(code);
		HashMap<String, Object> memberInfo = oAuthService.getMemberInfo(accessToken);
		String memberId=memberInfo.get("memberId").toString();
		String nickname=memberInfo.get("nickname").toString();
		
		boolean isInDB = memberService.getMember( memberId, memberId );
		if (!isInDB) {
			memberService.registerMember(new Member(memberId,nickname,"임시 닉네임"));
		}
		return true;
	}
}
