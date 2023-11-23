package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalVO {
	private long appr_no; //승인번호
	private long vaca_no; //휴가번호
	private String car_no; //차량번호
	private long emp_no; //사번
	private boolean true_or_not; //확인 유무
}
