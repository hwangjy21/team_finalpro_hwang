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
	private long appr_no; //���ι�ȣ
	private long vaca_no; //�ް���ȣ
	private String car_no; //������ȣ
	private long emp_no; //���
	private boolean true_or_not; //Ȯ�� ����
}
