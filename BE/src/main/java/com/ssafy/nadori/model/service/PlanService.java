package com.ssafy.nadori.model.service;

import java.util.List;

import com.ssafy.nadori.model.dto.Plan;

public interface PlanService {

	//등록
	boolean registerPlan(Plan plan) throws Exception; 
	
	//수정
	boolean modifyPlan(Plan plan) throws Exception;
	
	//삭제
	boolean removePlan(int planId) throws Exception;
	
	//조회
	List<Plan> getPlans() throws Exception;
	
	//조회 - 키워드
	List<Plan> getPlansByKeyword(String keyword) throws Exception;
	
	//조회 - 유저네임
	List<Plan> getPlansByUsername(String username) throws Exception;
}
