package com.ssafy.nadori.model.service;

import java.util.List;

import com.ssafy.nadori.model.dto.Plan;
import com.ssafy.nadori.model.dto.RequestPlan;

public interface PlanService {

	//등록
	int registerPlan(RequestPlan plan) throws Exception; 
	
	//수정
	int modifyPlan(RequestPlan plan) throws Exception;
	
	//삭제
	int removePlan(int planId) throws Exception;
	
	//조회
	Plan getPlan(int planId) throws Exception;
	
	//조회
	List<Plan> getPlans() throws Exception;
	
	//조회 - 키워드
	List<Plan> getPlansByKeyword(String keyword) throws Exception;
	
	//조회 - 유저네임
	List<Plan> getPlansByUsername(String username) throws Exception;
}
