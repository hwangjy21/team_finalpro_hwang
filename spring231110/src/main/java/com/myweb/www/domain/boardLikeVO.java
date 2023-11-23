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
public class boardLikeVO {
	private String id; //좋아요한 아이디
	private long bno; //좋아요 게시물 번호
	private long cmtNo; //좋아요 댓글 번호
}
