package com.ssafy.nadori.model.service;

import java.util.List;

import com.ssafy.nadori.model.dto.Plan;

public interface PlanService {

	//등록
	boolean registerPlan(Plan plan) throws Exception; 
	
	//수정
	boolean modifyPlan(Plan plan) throws Exception;
	
	//삭제
	boolean removePlan(int[] planId) throws Exception;
	
	//조회
	List<Plan> getPlans() throws Exception;
	
	//조회 - 제목 포함 조회
	List<Plan> getPlans(String title) throws Exception;
	
	//조회 - planId
	Plan getPlan(int planId) throws Exception;
}
