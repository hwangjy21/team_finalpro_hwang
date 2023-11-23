package com.myweb.www.repository;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.ApprovalDTO;
import com.myweb.www.domain.ApprovalVO;




public interface ApprovalDAO {


	

	

		boolean approval_club(ApprovalVO avo);

		boolean approvalExist(ApprovalVO avo);
	

}
