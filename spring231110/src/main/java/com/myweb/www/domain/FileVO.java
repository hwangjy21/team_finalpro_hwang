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
public class FileVO {
	private String uuid; 
	private String saveDir;
	private String fileName;
	private int fileType;
	private long depBno;
	private long totalBno;
	private String carNo;
	private long empNo;
	private long fileSize;
	private String regAt;
}
