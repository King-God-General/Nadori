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
	public boolean registerContent(Content content) throws Exception {
		return contentDao.insertContent(content)>0;
	}

	@Override
	public boolean modifyContent(Content content) throws Exception {
		return contentDao.updateContent(content)>0;
	}

	@Override
	public boolean removeContent(Content content) throws Exception {
		return contentDao.deleteContent(content)>0;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Content> getContents() throws Exception {
		return contentDao.selectContents();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Content> getContents(int dayNum) throws Exception {
		return contentDao.selectContentsByDayNum(dayNum);
	}

}
