package com.ssafy.nadori.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.nadori.model.dto.Content;


@Repository
public interface ContentDAO {
		//등록
		int insertContent(Content content) throws SQLException;
		
		//수정
		int updateContent(Content content) throws SQLException;
		
		//삭제
		int deleteContent(Content content) throws SQLException;
		
		//조회
		List<Content> selectContents() throws SQLException;
		
		//조회 - dayNum
		List<Content> selectContentsByDayNum(int dayNum) throws SQLException;

}
