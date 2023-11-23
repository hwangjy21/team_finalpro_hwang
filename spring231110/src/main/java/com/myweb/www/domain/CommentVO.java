package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class CommentVO {
	private long cmtNo; //댓글번호
	private long bno; //글번호
	private int empNo; //사번 
	private String empNm; //댓글쓴이
	private String content; //댓글내용
	private String regDate;
	private String modDate;
}
