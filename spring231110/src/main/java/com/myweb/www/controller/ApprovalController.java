package com.myweb.www.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.ApprovalVO;
import com.myweb.www.service.ApprovalService;
import com.myweb.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/approval/*")
@Slf4j
public class ApprovalController {
	private final ApprovalService asv;
	private final MemberService msv;

	@Autowired
	public ApprovalController(ApprovalService asv, MemberService msv) {
		this.asv = asv;
		this.msv = msv;
	}

	@PutMapping(value = "/{clubCd}/{userId}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> club(@PathVariable("clubCd") String clubCd, @PathVariable("userId") String userId) {
	    log.info("동호회 서비스 들어옴 " + clubCd);

	    log.info("동호회 id " + userId);
	    ApprovalVO avo = new ApprovalVO();
	    avo.setClubNo(clubCd + userId);

	    boolean approvalResult = false;
	    try {
	        boolean approvalExist = asv.approvalExist(avo);
	        if (approvalExist) {
	            approvalResult= asv.approval_club(avo);

	            log.info("approval 결과 : " + approvalResult);
	        } else {
	            return new ResponseEntity<>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (Exception e) {
	        log.error("Error during club approval", e);
	        return new ResponseEntity<>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return approvalResult ? new ResponseEntity<>("success", HttpStatus.OK)
	                    : new ResponseEntity<>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
