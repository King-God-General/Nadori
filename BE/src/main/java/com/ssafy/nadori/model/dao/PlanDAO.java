package com.ssafy.nadori.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.nadori.model.dto.Plan;

public interface PlanDAO {
		//등록
		int insertPlan(Plan plan) throws SQLException; 
		
		//수정
		int updatePlan(Plan plan) throws SQLException;
		
		//삭제
		int deletePlan(int[] planId) throws SQLException;
		
		//조회
		List<Plan> selectPlans() throws SQLException;
		
		//조회 - 제목 포함 조회
		List<Plan> selectPlansByTitle(String title) throws SQLException;
		
		//조회 - planId
		Plan selectPlan(int planId) throws SQLException;

}
