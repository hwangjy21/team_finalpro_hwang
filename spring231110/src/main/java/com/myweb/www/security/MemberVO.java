package com.myweb.www.security;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class MemberVO {
	private long empNo; //사원번호
	private String id; //아이디
	private String pw; //비밀번호
	private String addr; //주소
	private String phone; //전화번호
	private String depCd; //부서코드
	private String empNm; //사원명
	private String empBirth; //생년월일
	private String lastLogin; //마지막 로그인

	private String clubCd;// 동호회 코드


	private List<AuthVO> authVOList;
	
}
