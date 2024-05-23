package com.ssafy.nadori.model.service;

import java.util.HashMap;

import com.ssafy.nadori.model.dto.Member;

public interface MemberService {
	//등록
	boolean registerMember(Member member);
	
	//삭제
	boolean removeMember(String memberId);
	
	//수정
	boolean modifyMember(Member member);
	
	//조회
	Member getMember(String memberId);
}
