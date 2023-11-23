package com.myweb.www.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.ClubService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/club/*")
@Controller
public class ClubController {
	
	
	@Inject
	private ClubService clsv;
		@GetMapping("list")
		public String list(Model model, PagingVO PagingVO,RedirectAttributes reAttr) {
			log.info(">>>>>pagingVO>>" + PagingVO);
			model.addAttribute("list", clsv.getList(PagingVO));
			
			/* int totalCount = clsv.getTotalCount(PagingVO); */
			/* PagingHandler ph = new PagingHandler(PagingVO, totalCount); */
			
			/* model.addAttribute("ph", ph); */
			return "/club/club_apply";
		}
}
