package com.myweb.www.service;

import com.myweb.www.domain.ApprovalVO;

public interface ApprovalService {

	boolean approval_club(ApprovalVO avo);

	boolean approvalExist(ApprovalVO avo);

}
