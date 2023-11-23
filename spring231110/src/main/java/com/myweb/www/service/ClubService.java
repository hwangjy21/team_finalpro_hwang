package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.ClubVO;
import com.myweb.www.domain.PagingVO;

public interface ClubService {

	List<ClubVO> getList(PagingVO pagingVO);

	int getTotalCount(PagingVO pagingVO);

}
