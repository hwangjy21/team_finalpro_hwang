package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

public interface CommentDAO {

	int insert(CommentVO cvo);  //o

//	List<CommentVO> selectAll(long bno);

	int delete(long cmtNo); //O

	int deleteCommentAll(long bno);

	int update(CommentVO cvo); //o

	int selectOneBnoTotalCount(long bno); //o

	List<CommentVO> selectListPaging(@Param("bno") long bno,@Param("pgvo") PagingVO pgvo);//o

	int commentLikeCheck(@Param("cmtNo") long cmtNo,@Param("authId") String authId);

	void deleteCommentLike(@Param("cmtNo") long cmtNo,@Param("id") String id);

	void updateLikeQty(@Param("cmtNo") long cmtNo,@Param("num") int num);

	void addCommentLike(@Param("cmtNo") long cmtNo,@Param("id") String id);

	int selectCmtCount(long bno);

	int cmtLikeQty(long cmtNo);


 



}
