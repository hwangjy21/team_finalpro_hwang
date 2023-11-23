package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;

public interface CommentService {

	int addComment(CommentVO cvo); //O

//	List<CommentVO> getList(long bno);

	int remove(long cmtNo); //o

	void deleteCommentAll(long bno);

	int modify(CommentVO cvo); //o

	PagingHandler getList(long bno, PagingVO pgvo, String authId); //0

	int boardLikeCheck(long cmtNo, String id);

	void deleteBoardLike(long cmtNo, String id);

	void addBoardLike(long cmtNo, String id);

	int getCmtLikeQty(long cmtNo);


}
