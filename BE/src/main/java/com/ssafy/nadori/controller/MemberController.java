package com.ssafy.nadori.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nadori.model.service.MemberService;
import com.ssafy.nadori.model.service.OAuthService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
	private final MemberService memberService;
//	private final OAuthService oAuthService;
	
	@GetMapping("/login/kakao")
	public void doKakaoLogin(@RequestParam(name = "code", required = false) String code){
		System.out.println("code: "+code);
		

	}
}
