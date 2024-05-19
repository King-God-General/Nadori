package com.ssafy.nadori.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nadori.model.dto.Plan;
import com.ssafy.nadori.model.dto.RequestPlan;
import com.ssafy.nadori.model.service.PlanService;

@RequestMapping("/api/plan")
@RestController
public class PlanController {
	public final PlanService planService;
	
	public PlanController(PlanService planService) {
		this.planService=planService;
	}
	
	@GetMapping
	protected ResponseEntity<List<Plan>> getPlanList(@RequestParam(name = "keyword", required = false) String keyword,
	                                                 @RequestParam(name = "username", required = false) String username) throws Exception {
	    if (keyword != null) {
	        return new ResponseEntity<List<Plan>>(planService.getPlansByKeyword(keyword), HttpStatus.OK);
	    } 
	    else if (username != null) {
	        return new ResponseEntity<List<Plan>>(planService.getPlansByUsername(username), HttpStatus.OK);
	    } 
	    else {
	        return new ResponseEntity<List<Plan>>(planService.getPlans(), HttpStatus.OK);
	    }
	}
	
	@PostMapping
	protected ResponseEntity<Integer> postPlan(@RequestBody RequestPlan plan) throws Exception{
		return new ResponseEntity<Integer>(planService.registerPlan(plan), HttpStatus.OK);
	}
	
	@DeleteMapping("/{planId}")
	protected ResponseEntity<Integer> deletePlan(@PathVariable int planId) throws Exception{
		return new ResponseEntity<Integer>(planService.removePlan(planId), HttpStatus.OK);
	}
	
	@PutMapping
	protected ResponseEntity<Integer> putPlan(@RequestBody RequestPlan plan) throws Exception{
		return new ResponseEntity<Integer>(planService.modifyPlan(plan), HttpStatus.OK);
	}
	
}
