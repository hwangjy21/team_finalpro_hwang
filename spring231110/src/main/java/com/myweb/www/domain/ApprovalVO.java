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
	private long apprNo; 
	private long vacaNo; 
	private String carNo; 
	private long empNo;
	private String ClubNo; 
	private boolean trueOrNot; 
}
