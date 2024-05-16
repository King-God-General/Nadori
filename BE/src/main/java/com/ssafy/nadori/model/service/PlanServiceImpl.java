package com.ssafy.nadori.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.nadori.model.dao.PlanDAO;
import com.ssafy.nadori.model.dto.Plan;

@Transactional
@Service
public class PlanServiceImpl implements PlanService{

	private PlanDAO planDao;
	public PlanServiceImpl(PlanDAO planDao) {
		this.planDao = planDao;
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
	public boolean removePlan(int[] planId) throws Exception {
		return planDao.deletePlan(planId)>0;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Plan> getPlans() throws Exception {
		return planDao.selectPlans();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Plan> getPlans(String title) throws Exception {
		return planDao.selectPlansByTitle(title);
	}

	@Transactional(readOnly = true)
	@Override
	public Plan getPlan(int planId) throws Exception {
		return planDao.selectPlan(planId);
	}

	
}
