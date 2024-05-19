package com.ssafy.nadori.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.nadori.model.dao.ContentDAO;
import com.ssafy.nadori.model.dto.Content;

@Transactional
@Service
public class ContentServiceImpl implements ContentService{

	private ContentDAO contentDao;
	public ContentServiceImpl(ContentDAO contentDao) {
		super();
		this.contentDao = contentDao;
	}
	
	@Override
	public int registerContents(int planId, List<Content> contents) throws Exception {
		int savedContents=0;
		for (Content c: contents) {
			c.setPlanId(planId);
			savedContents+=contentDao.insertContent(c);	
		}
		return savedContents;
	}

	@Override
	public int removeContents(int planId) throws Exception {
		return contentDao.deleteContents(planId);
	}

	@Override
	public List<Content> getContents(int planId) throws Exception {
		return contentDao.selectContents(planId);
	}

	@Override
	public String modifyContents(int planId, List<Content> contents) throws Exception {
		int removeResult = removeContents(planId);
		int registerResult = 0;
		if (removeResult>0) {
			registerResult =  registerContents(planId, contents);
		}
		return removeResult+"개의 과거 데이터를 삭제한 후, "+ registerResult+"개의 데이터를 추가하였습니다.";
	}

}
