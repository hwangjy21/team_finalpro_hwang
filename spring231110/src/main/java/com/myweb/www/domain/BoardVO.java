package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	private long bno;//글번호 
	private long empNo;//사번 
	private String id;//작성자 //
	private String title;//제목 //
	private String content;//내용 //
	private String regDate;//등록일 //
	private String modDate;//수정일
	private String boardType;//게시판 종류(익명,보드,동호회)
	private String clubCd;//동호회 코드
	private String depCd;//부서코드
	private int cmtQty;//댓글개수
	private int readQty;//조회수
	private int fileQty;//파일수
	private int likeQty;//게시글 좋아요수
	private boolean likeCheck;// 로그인한 아이디가 한 게시물 좋아요한 유무
}
