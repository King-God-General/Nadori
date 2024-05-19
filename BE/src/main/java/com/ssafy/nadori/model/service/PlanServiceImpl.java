package com.ssafy.nadori.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.nadori.model.dao.ContentDAO;
import com.ssafy.nadori.model.dao.PlanDAO;
import com.ssafy.nadori.model.dto.Plan;
import com.ssafy.nadori.model.dto.RequestPlan;

@Transactional
@Service
public class PlanServiceImpl implements PlanService{

	private PlanDAO planDao;
	private ContentDAO contentDAO;
	
	public PlanServiceImpl(PlanDAO planDao, ContentDAO contentDAO) {
		this.planDao = planDao;
		this.contentDAO = contentDAO;
	}
	
	@Override
	public int registerPlan(RequestPlan plan) throws Exception {
		planDao.insertPlan(plan);
		return plan.getPlanId();
	}

	@Override
	public int modifyPlan(RequestPlan plan) throws Exception {
		return planDao.updatePlan(plan);
	}

	@Override
	public int removePlan(int planId) throws Exception {
		int removedContents=contentDAO.deleteAllContents(planId);
		int removedPlan=planDao.deletePlan(planId);
		System.out.println(removedPlan+"개의 plan에 대한 "+removedContents+"개의 content가 삭제되었습니다.");
		return removedPlan;
	}

	@Override
	public List<Plan> getPlans() throws Exception {
		return planDao.selectPlans();
	}

	@Override
	public List<Plan> getPlansByKeyword(String keyword) throws Exception {
		return planDao.selectPlansByKeyword(keyword);
	}

	@Override
	public List<Plan> getPlansByUsername(String username) throws Exception {
		return planDao.selectPlansByUsername(username);
	}

	
}
