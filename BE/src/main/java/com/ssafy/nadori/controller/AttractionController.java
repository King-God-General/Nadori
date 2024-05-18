package com.ssafy.nadori.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nadori.model.dto.Attraction;
import com.ssafy.nadori.model.service.AttractionService;

@RequestMapping("/api/attraction")
@RestController
public class AttractionController {
	public final AttractionService attractionService;
	
	public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }
	 
	@GetMapping
	protected ResponseEntity<List<Attraction>> getAttractionList() throws Exception {
		return new ResponseEntity<List<Attraction>>(attractionService.searchAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{sidoCode}/{gugunCode}/{contentTypeId}")
	protected ResponseEntity<List<Attraction>> getAttractionListByMultiCondition(@PathVariable Map<String,Object> condition) throws Exception {
	    System.out.println(condition.get("sidoCode")+" "+condition.get("gugunCode")+" "+condition.get("contentTypeId"));
		return new ResponseEntity<List<Attraction>>(attractionService.searchByMultiCondition(condition), HttpStatus.OK);
	}
}
