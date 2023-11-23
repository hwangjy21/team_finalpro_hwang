package com.myweb.www.security;

import java.util.List;

import com.myweb.www.domain.ClubVO;
import com.myweb.www.domain.DepartmentVO;

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
	private List<MemberVO> mlist;
	private List<DepartmentVO> dlist;
	private List<ClubVO> clist;
}
