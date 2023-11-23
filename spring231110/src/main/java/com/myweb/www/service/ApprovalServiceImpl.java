package com.myweb.www.service;



import javax.inject.Inject;

import org.springframework.stereotype.Service;


import com.myweb.www.domain.ApprovalVO;
import com.myweb.www.domain.ClubVO;
import com.myweb.www.repository.ApprovalDAO;
import com.myweb.www.security.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApprovalServiceImpl implements ApprovalService {
    private final ApprovalDAO adao;

    public ApprovalServiceImpl(ApprovalDAO adao) {
        this.adao = adao;
    }

    @Override
    public boolean approval_club(ApprovalVO avo) {
        log.info("제발 avo "+avo);
        
        boolean result = adao.approval_club(avo);

        if (!result) {
            log.error("Club approval failed");
            return false;
        }

        return true;
    }

	@Override
	public boolean approvalExist(ApprovalVO avo) {
		

        
        boolean result = adao.approvalExist(avo);
        log.info("승인이 존재하는지"+result);
        if (!result) {
            log.error("Club approval Exit");
            return false;
        }

        return true;
	}



}