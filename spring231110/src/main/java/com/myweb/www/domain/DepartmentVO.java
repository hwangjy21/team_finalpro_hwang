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
public class DepartmentVO {
 private String depCd; //부서코드
 private String depNm; //부서명
 private String depPhone; //부서전화번호
 private String depLoca; //부서위치
}
