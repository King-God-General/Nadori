package com.ssafy.nadori.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nadori.model.dto.Attraction;
import com.ssafy.nadori.model.service.AttractionService;
import com.ssafy.nadori.model.service.TourApiService;

@RequestMapping("/api/Attraction")
@RestController
public class AttractionController {
	public final AttractionService attractionService;
	public final TourApiService tourApiService;
	
	public AttractionController(AttractionService attractionService, TourApiService tourApiService) {
        this.attractionService = attractionService;
        this.tourApiService = tourApiService;
    }
	 
	//외부 api 호출
	@PutMapping("/update")
	protected ResponseEntity<List<Attraction>> updateAttractionList() throws Exception {
		//return new ResponseEntity<Integer>(attractionService.updateAttraction( tourApiService.getAttraction() ), HttpStatus.OK);
		return new ResponseEntity<List<Attraction>>(tourApiService.getAttraction(), HttpStatus.OK);
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
