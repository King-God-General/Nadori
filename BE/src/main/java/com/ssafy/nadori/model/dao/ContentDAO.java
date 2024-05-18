package com.ssafy.nadori.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.nadori.model.dto.Content;

@Repository
public interface ContentDAO {
		//등록
		int insertContent(Content content);
		
		//삭제 - planId
		int deleteAllContents(int planId);
		
		//조회 - planId
		List<Content> selectContentsByPlanId(int planId);
		//조회 - planId+dayNum
		List<Content> selectContentsByDayNum(Map<String,Object> condition);

}
