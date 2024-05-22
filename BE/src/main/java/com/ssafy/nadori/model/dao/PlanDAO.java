package com.ssafy.nadori.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.nadori.model.dto.Plan;
import com.ssafy.nadori.model.dto.RequestPlan;

@Repository
public interface PlanDAO {
		//등록
		int insertPlan(RequestPlan plan); 
		
		//수정
		int updatePlan(RequestPlan plan);
		
		//삭제
		int deletePlan(int planId);
		int deletePlans(String memberId);
		
		//조회
		Plan selectPlan(int planId);
		//조회
		List<Plan> selectPlans();
		//조회 - 키워드
		List<Plan> selectPlansByKeyword(String keyword);
		//조회 - 유저네임
		List<Plan> selectPlansByUsername(String username);

}
