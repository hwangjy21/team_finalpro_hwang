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
	private String empId; //댓글쓴이
	private String content; //댓글내용
	private String regDate;
	private String modDate;
	private int likeQty;//댓글 좋아요수
	private boolean likeCheck;//로그인한 아이디가 한 댓글 좋아요한 유무
}
