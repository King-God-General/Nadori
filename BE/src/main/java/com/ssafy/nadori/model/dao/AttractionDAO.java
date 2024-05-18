package com.ssafy.nadori.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.nadori.model.dto.Attraction;

@Repository
public interface AttractionDAO {
	List<Attraction> selectAll();
	List<Attraction> selectByMultiCondition(Map<String,Object> condition);
	Integer insertAttraction(List<Attraction> list);
}