package com.ssafy.nadori.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.nadori.model.dao.AttractionDAO;
import com.ssafy.nadori.model.dto.Attraction;

@Transactional
@Service
public class AttractionServiceImpl implements AttractionService {

	private AttractionDAO attractionDAO;
	
	public AttractionServiceImpl(AttractionDAO attractionDAO) {
		this.attractionDAO = attractionDAO;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Attraction> searchAll() {
		return attractionDAO.selectAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Attraction> searchByMultiCondition(Map<String, Object> condition) {
		return attractionDAO.selectByMultiCondition(condition);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Integer updateAttraction(List<Attraction> list) {
		return attractionDAO.insertAttraction(list);
	}

}
