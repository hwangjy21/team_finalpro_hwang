package com.myweb.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.service.ApprovalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/approval/*")
@Controller
public class ApprovalController {
	private ApprovalService asv;
	
	@GetMapping("/clubApproval")
	public void clubApproval() {}
	
}
