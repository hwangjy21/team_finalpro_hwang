package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	void insert(BoardVO bvo);

	List<BoardVO> selectAllDepartBoard(@Param("depCd") String depCd,@Param("pgvo") PagingVO pgvo);

	String getUserDepNm(String depCd);

	String getUserClubNm(String clubCd);

	List<BoardVO> selectAllClubBoard(@Param("clubCd") String clubCd,@Param("pgvo") PagingVO pgvo);

	List<BoardVO> selectAllAnonyBoard();

	BoardVO selectOne(long bno);

	int departTotalCount(@Param("depCd") String depCd,@Param("pgvo") PagingVO pgvo);

	int clubTotalCount(@Param("clubCd") String clubCd,@Param("pgvo") PagingVO pgvo);

	void updateCmtCount();

	void updateReadQty(long bno);

	int boardLikeCheck(@Param("bno") long bno,@Param("id") String id);

	void deleteBoardLike(@Param("bno") long bno,@Param("id") String id);

	void addBoardLike(@Param("bno") long bno,@Param("id") String id);

	void updateLikeQty(@Param("bno") long bno,@Param("num") int num);






}
