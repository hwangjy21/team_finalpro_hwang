package com.myweb.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
	private CommentDAO cdao;
	
	@Autowired
	public CommentServiceImpl(CommentDAO cdao) {
		this.cdao =cdao;
	}

	@Override
	public int addComment(CommentVO cvo) {
//		cdao.cmt_update(cvo.getBno());
		return cdao.insert(cvo);
	}

//	@Override
//	public List<CommentVO> getList(long bno) {
//		return cdao.selectAll(bno);
//	}

	@Override
	public int remove(long cmtNo) {
		return cdao.delete(cmtNo);
	}

	@Override
	public void deleteCommentAll(long bno) {
		cdao.deleteCommentAll(bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.update(cvo);
	}

	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo,String authId) {
		// totalCount 구하기
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		// Comment List 찾아오기
		List<CommentVO> list = cdao.selectListPaging(bno,pgvo);
		// list에 likeCheck set	
		for(CommentVO cvo : list) {
			int isOk=0;
			isOk=cdao.commentLikeCheck(cvo.getCmtNo(),authId);
			if(isOk>0){
				cvo.setLikeCheck(true);
			}else {
				cvo.setLikeCheck(false);
			}			
			
			cvo.setLikeQty(cdao.cmtLikeQty(cvo.getCmtNo())); //좋아요 수 set
		}
	log.info("pgvo>>{} ",pgvo);
	log.info("totalCount>>{} ",totalCount);
	log.info("list>>{} ",list);
		// pagingHandler 값 완성 후 리턴
		PagingHandler ph = new PagingHandler(pgvo, totalCount,list);
		
		return ph;
	}

	//댓글 좋아요 확인(1이면 이미 체크, 0이면 체크안되어있는거)
	@Override
	public int boardLikeCheck(long cmtNo, String id) {
		return cdao.commentLikeCheck(cmtNo,id);
	}

	//댓글 좋아요 취소
	@Override
	public void deleteBoardLike(long cmtNo, String id) {
		int num=-1;
		cdao.deleteCommentLike(cmtNo,id);//board_like테이블에 delete
		cdao.updateLikeQty(cmtNo,num);//comment 테이블의 likeQty에 -1해주기
	}

	//댓글 좋아요
	@Override
	public void addBoardLike(long cmtNo, String id) {
		int num=1;
		cdao.addCommentLike(cmtNo,id);//board_like테이블에 add
		cdao.updateLikeQty(cmtNo,num);//comment 테이블의 likeQty에 +1해주기
	
	}

	@Override
	public int getCmtLikeQty(long cmtNo) {
		return cdao.cmtLikeQty(cmtNo);
	}

}
