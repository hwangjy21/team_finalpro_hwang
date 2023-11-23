package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

//	List<CommentVO> selectAll(long bno);

	int delete(long cno);

	int deleteCommentAll(long bno);

	int update(CommentVO cvo);

	int selectOneBnoTotalCount(long bno);

	List<CommentVO> selectListPaging(@Param("bno") long bno,@Param("pgvo") PagingVO pgvo);

//	void cmt_update(long bno);



}
