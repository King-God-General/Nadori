package com.ssafy.nadori.model.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.nadori.model.dao.ContentDAO;
import com.ssafy.nadori.model.dao.MemberDAO;
import com.ssafy.nadori.model.dao.PlanDAO;
import com.ssafy.nadori.model.dto.Member;

@Transactional
@Service
public class MemberServiceImpl implements MemberService{
	private MemberDAO memberDao;
	private PlanDAO planDao;
	private ContentDAO contentDao;
	
	public MemberServiceImpl(MemberDAO memberDao, PlanDAO planDao, ContentDAO contentDao) {
		super();
		this.memberDao = memberDao;
		this.planDao = planDao;
		this.contentDao = contentDao;
	}
	
	@Override
	public boolean registerMember(Member member) {
		return memberDao.insertMember(member)>0;
	}

	@Override
	public boolean removeMember(String memberId) {
		contentDao.deleteContentsByMemberId(memberId);
		planDao.deletePlans(memberId);
		return memberDao.deleteMember(memberId)>0;
	}

	@Override
	public boolean modifyMember(Member member) {
		return memberDao.updateMember(member)>0;
	}

	@Override
	public Member getMember(String memberId) {
		return memberDao.selectMember(memberId);
	}

}
