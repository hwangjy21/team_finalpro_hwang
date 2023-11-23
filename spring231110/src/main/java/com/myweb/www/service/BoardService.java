package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	void write(BoardVO bvo);

	String getUserDepCd(String id);

	List<BoardVO> getDepartList(String depCd, PagingVO pgvo);

	String getUserDepNm(String depCd);
	
	

	String getUserClubCd(String id);

	String getUserClubNm(String clubCd);

	List<BoardVO> getClubList(String clubCd, PagingVO pgvo);

	List<BoardVO> getAnonyList();

	BoardVO getBoardDetail(long bno);

	int getDepartTotalCount(String depCd, PagingVO pgvo);

	int getClubTotalCount(String clubCd, PagingVO pgvo);




}
