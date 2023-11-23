package com.myweb.www.domain;

import com.myweb.www.security.MemberVO;

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
public class MemberDTO {
	private MemberVO mvo;
	private DepartmentVO dvo;
	private ClubVO cvo;
}
