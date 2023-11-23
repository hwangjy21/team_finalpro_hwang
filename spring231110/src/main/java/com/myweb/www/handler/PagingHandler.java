package com.myweb.www.handler;

import java.util.List;

import com.myweb.www.domain.ClubVO;
import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingHandler {
	
	private int startPage;//화면에 보여지는 시작 페이지네이션 번호
	private int endPage;//화면에 보여지는 끝 페이지네이션 번호
	
	private int realEndPage;//페이지네이션 진짜 끝번호
	private boolean prev,next; //이전, 다음 존재 여부
	private int totalCount;//총 게시글 수
	private PagingVO pgvo;
	
	private String order;//정렬기준
	private List<CommentVO> cmtList;
	
	// 현재 페이지 값 가져오기 용도 / totalCount DB에서 조회 매개변수로
	public PagingHandler(int totalCount, PagingVO pgvo) {
		this.totalCount = totalCount;
		this.pgvo = pgvo;
	
		// 전체 글 수 / 한 페이지에 표시되는 게시글 수 => 올림(ceil)
		this.endPage=(int)Math.ceil(pgvo.getPageNo()/(double)10)*10;
		
		this.startPage=endPage-9;
		
		this.realEndPage=(int)Math.ceil(totalCount/(double)pgvo.getQty());
		
		if(endPage>realEndPage) {
			this.endPage=this.realEndPage;
		}
		// startPage는 1,11,21,31...
		this.prev=this.startPage>1;
		
		this.next=this.endPage<realEndPage;
	
	}
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList) {

		this(totalCount, pgvo);	
		this.cmtList = cmtList;
	

	}		
}