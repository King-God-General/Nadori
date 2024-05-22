package com.ssafy.nadori.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.RedirectView;

import com.ssafy.nadori.model.dto.Member;
import com.ssafy.nadori.model.dto.RequestPlan;
import com.ssafy.nadori.model.service.MemberService;
import com.ssafy.nadori.model.service.OAuthService;
import com.ssafy.nadori.model.service.PlanService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
	private final MemberService memberService;
	private final OAuthService oAuthService;
	
	@GetMapping("/login/kakao")
	public ResponseEntity<Member> loginOrSignUp(@RequestParam(name = "code", required = false) String code){	
		String[] tokens = oAuthService.getTokens(code);
		HashMap<String, String> memberInfo = oAuthService.getMemberInfo(tokens[0]);
		String memberId=memberInfo.get("memberId").toString();
		String nickname=memberInfo.get("nickname").toString();
		
		System.out.println("accessToken :"+tokens[0]);
		System.out.println("memberId :"+memberId);
		System.out.println("nickname :"+nickname);
		
		Member newMember = new Member(memberId,nickname,"임시 닉네임",tokens[0], tokens[1]); 
		Member oldMember = memberService.getMember( memberId );
		if (oldMember==null)  {
			memberService.registerMember(newMember);
		}
		else if( newMember.getNickname() != oldMember.getNickname() ) {
			memberService.modifyMember(newMember);
		}
		else {}
		
		return new ResponseEntity<Member>(newMember, HttpStatus.OK);
	}
	
	@DeleteMapping("/withdraw/kakao/{memberId}")
	public ResponseEntity<String> withdraw(@PathVariable String memberId){	
		boolean result = memberService.removeMember(memberId);
		if (result) {
			return new ResponseEntity<String>("회원 탈퇴에 성공했습니다.", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("회원 탈퇴에 실패했습니다.", HttpStatus.OK);			
		}
	}
	
	@PutMapping("/update/kakao")
	public ResponseEntity<String> updateMemberInfo(@RequestBody Member member){	
		boolean result = memberService.modifyMember(member);
		if (result) {
			return new ResponseEntity<String>("회원 정보 수정에 성공했습니다.", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("회원 정보 수정에 실패했습니다.", HttpStatus.OK);			
		}
	}
}
