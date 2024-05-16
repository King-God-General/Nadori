package com.ssafy.nadori.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.nadori.model.dto.Attraction;

public interface AttractionService {
	List<Attraction> searchAll();
	List<Attraction> searchByMultiCondition(Map<String,Object> condition);
	Integer updateAttraction(List<Attraction> list);
}
