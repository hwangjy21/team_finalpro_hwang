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
public class ClubVO {

	private String clubCd;
	private String clubNm;

	private String clubIntro;

	private int memberCnt;
	private int memberLimitCnt;


}
