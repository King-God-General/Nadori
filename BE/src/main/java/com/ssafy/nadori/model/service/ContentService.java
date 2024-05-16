package com.ssafy.nadori.model.service;

import java.util.List;

import com.ssafy.nadori.model.dto.Content;

public interface ContentService {

	//등록
	boolean registerContent(Content content) throws Exception;
	
	//수정
	boolean modifyContent(Content content) throws Exception;
	
	//삭제
	boolean removeContent(Content content) throws Exception;
	
	//조회
	List<Content> getContents() throws Exception;
	
	//조회 - dayNum
	List<Content> getContents(int dayNum) throws Exception;
}
