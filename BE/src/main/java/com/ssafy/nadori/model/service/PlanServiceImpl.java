package com.ssafy.nadori.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.nadori.model.dao.ContentDAO;
import com.ssafy.nadori.model.dao.PlanDAO;
import com.ssafy.nadori.model.dto.Plan;

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
	public boolean registerPlan(Plan plan) throws Exception {
		return planDao.insertPlan(plan)>0;
	}

	@Override
	public boolean modifyPlan(Plan plan) throws Exception {
		return planDao.updatePlan(plan)>0;
	}

	@Override
	public boolean removePlan(int planId) throws Exception {
		contentDAO.deleteAllContents(planId);
		return planDao.deletePlan(planId)>0;
	}

	@Override
	public List<Plan> getPlans() throws Exception {
		System.out.println("service");
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
