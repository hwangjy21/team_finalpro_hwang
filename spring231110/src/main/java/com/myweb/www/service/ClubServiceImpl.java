package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.ClubVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.ClubDAO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ClubServiceImpl implements ClubService {
	@Inject
	private ClubDAO cldao;

	@Override
	public List<ClubVO> getList(PagingVO pagingVO) {
		log.info("동호회 리스트 서비스 들어옴");
		List<ClubVO> list = cldao.list(pagingVO);
		
		return list;
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {
		log.info("동호회 리스트 총개수 서비스 들어옴");
	return cldao.totalCount(pagingVO);
	}
}
