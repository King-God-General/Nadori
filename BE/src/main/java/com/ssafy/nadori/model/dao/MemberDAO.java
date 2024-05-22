package com.ssafy.nadori.model.dao;

import org.springframework.stereotype.Repository;

import com.ssafy.nadori.model.dto.Member;

@Repository
public interface MemberDAO {
	//등록
	int insertMember(Member member);
	//삭제
	int deleteMember(String memberId);
	//수정
	int updateMember(Member member);
	//조회
	Member selectMember(String memberId);
}
