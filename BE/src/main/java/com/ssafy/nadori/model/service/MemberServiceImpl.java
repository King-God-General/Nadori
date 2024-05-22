package com.ssafy.nadori.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.nadori.model.dao.MemberDAO;
import com.ssafy.nadori.model.dto.Member;

@Transactional
@Service
public class MemberServiceImpl implements MemberService{
	private MemberDAO memberDao;
	public MemberServiceImpl(MemberDAO memberDao) {
		super();
		this.memberDao = memberDao;
	}
	
	@Override
	public int registerMember(Member member) {
		return memberDao.insertMember(member);
	}

	@Override
	public int removeMember(Member member) {
		return memberDao.deleteMember(member);
	}

	@Override
	public int modifyMember(Member member) {
		return memberDao.updateMember(member);
	}

	@Override
	public boolean getMember(String memberId, String nickname) {
		return memberDao.selectMember(memberId, nickname)>0;
	}

}
