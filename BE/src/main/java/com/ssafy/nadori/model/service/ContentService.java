package com.ssafy.nadori.model.service;

import java.util.List;

import com.ssafy.nadori.model.dto.Content;

public interface ContentService {

	//등록
	int registerContents(int planId, List<Content> contents) throws Exception;
	
	//삭제
	int removeContents(int planId) throws Exception;
	
	//수정
	String modifyContents(int planId, List<Content> contents) throws Exception;
	
	//조회
	List<Content> getContents(int planId) throws Exception;
}
