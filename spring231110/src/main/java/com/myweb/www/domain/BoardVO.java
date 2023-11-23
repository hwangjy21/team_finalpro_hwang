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
	private int commentCnt;//댓글개수
	private int readCnt;//조회수
}
