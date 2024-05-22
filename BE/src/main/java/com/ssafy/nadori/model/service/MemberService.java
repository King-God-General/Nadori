package com.ssafy.nadori.model.service;

import com.ssafy.nadori.model.dto.Member;

public interface MemberService {
	//등록
	int registerMember(Member member);
	
	//삭제
	int removeMember(Member member);
	
	//수정
	int modifyMember(Member member);
	
	//조회
	boolean getMember(String id, String nickname);
}
