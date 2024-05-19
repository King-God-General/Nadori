package com.ssafy.nadori.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.nadori.model.dto.Content;

@Repository
public interface ContentDAO {
		//등록
		int insertContent(Content content);
		
		//삭제
		int deleteContents(int planId);
		
		//조회
		List<Content> selectContents(int planId);

}
