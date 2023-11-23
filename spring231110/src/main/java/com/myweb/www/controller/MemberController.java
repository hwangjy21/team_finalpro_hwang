package com.myweb.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.handler.ProfileFileHandler;
import com.myweb.www.security.MemberDTO;
import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/**")
@Controller
public class MemberController {

	@Inject
	private BCryptPasswordEncoder bcEncoder;

	@Inject
	private MemberService msv;
	
	@Inject
	private FileHandler fh;
	
	@Inject
	private ProfileFileHandler pfh;

	@GetMapping("/register")
	public void register() {
	}
	
	//사원 등록
	@PostMapping("/register")
	public String register(MemberVO mvo, Model m, @RequestParam(name="profile", required = false)MultipartFile[] files) {
		int isOk = 1;
		mvo.setPw(bcEncoder.encode(mvo.getPw())); // 암호화해서 넣음
		isOk *= msv.register(mvo);
		long empNo = msv.getMaxEmpNo();
		FileVO fvo = null;
		if(files[0] != null) {
			fvo = pfh.uploadFiles(files[0], empNo);
		}
		isOk *= msv.insert(empNo, fvo);
		log.info(isOk>0? "성공": "실패");
		return "index";
	}

	@GetMapping("/login")
	public void login() {
	}
	
	//로그인 실패 시
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		re.addAttribute("id", request.getAttribute("id"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/member/login"; 
	}

	//회원 리스트(확인)
	@GetMapping("/list")
	public String list(Model model, PagingVO pagingVO) {
		MemberDTO mdto = new MemberDTO();
		mdto.setMlist(msv.getMList(pagingVO));
		mdto.setClist(msv.getCList(pagingVO));
		mdto.setDlist(msv.getDList(pagingVO));
		model.addAttribute("list", mdto);
		// 총 페이지 갯수
		int totalCount = msv.getTotalCount(pagingVO);
		PagingHandler ph = new PagingHandler(totalCount, pagingVO);
		model.addAttribute("ph", ph);
		log.info("페이지정보" + ph);
		return "/member/list";

	}

	@GetMapping({ "/detail" })
	public void detail(Model model, @RequestParam("id") String id) {
		MemberVO mvo = msv.memberDetail(id);
		model.addAttribute("mvo", mvo);
	}

	@GetMapping("/modify")
	public void modify(@RequestParam("id") String id, Model m) {
		m.addAttribute("mvo", msv.memberDetail(id));
	}
	
	//회원 수정(확인)
	@PostMapping({ "/modify" })
	public void modify(MemberVO mvo, Model m, HttpServletRequest req, HttpServletResponse res) {
		int isOk = 3;
		if (mvo.getPw() == null || mvo.getPw().isEmpty()) {
			isOk = msv.modifyPwdEmpty(mvo);
		} else {
			mvo.setPw(bcEncoder.encode(mvo.getPw()));
			isOk = msv.modify(mvo);
		}
		logout(req, res);

		m.addAttribute("isOk", isOk);
	}
	
	//회원 탈퇴(확인)
	@GetMapping("/remove")
	public String removeMember(@RequestParam("id") String id, Model m, HttpServletRequest req,
			HttpServletResponse res) {
		int isOk = msv.remove(id);
		logout(req, res);
		m.addAttribute("isOkDel", isOk);
		return "index";

	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}


	// 비밀번호 변경 전 본인 인증 페이지로 이동
	@GetMapping("/checkMemberInfo")
	public void checkMemberInfo() {

	}
	
	//본인 인증 확인
	@PostMapping("/checkMemberInfo")
	public String postCheckMemberInfo(MemberVO mvo, RedirectAttributes re, Model m) {
		MemberVO detail = msv.getMemberDetail(mvo.getId());
		// 입력한 정보가 기존 정보와 전부 일치하는지 확인
		if (detail.getId().equals(mvo.getId()) && detail.getDepCd().equals(mvo.getDepCd())
				&& detail.getEmpNm().equals(mvo.getEmpNm()) && detail.getEmpBirth().equals(mvo.getEmpBirth())) {
			m.addAttribute("id", mvo.getId());
			return "/member/modifyPw";
		} else {
			m.addAttribute("msg", "해당 사원은 존재하지 않습니다.");
			m.addAttribute("url", "/member/checkMemberInfo");
			return "alert";
		}
	}

	@GetMapping("/modifyPw")
	public void modifyPw() {
	}
	
	//비밀번호 변경
	@PostMapping("/modifyPw")
	public String PostModifyPw(@RequestParam("pw") String pw, @RequestParam("pw2") String pw2,
			@RequestParam("id") String id, Model m) {
		if (pw.equals(pw2)) {
			String password = bcEncoder.encode(pw);
			log.info("id: " + id + "/ pw: " + pw);
			int isOk = msv.updatePw(id, password);
			if (isOk > 0) {
				m.addAttribute("msg", "변경이 완료되었습니다.");
				m.addAttribute("url", "/member/login");
				return "alert";
			} else {
				m.addAttribute("msg", "비밀번호 변경 실패.");
				m.addAttribute("url", "/member/modifyPw");
				return "alert";
			}
		} else {
			m.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			m.addAttribute("url", "/member/checkMemberInfo");
			return "alert";
		}
	}
	
	//아이디 일치하는지 확인
	@PostMapping(value = "/checkId", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> checkId(@RequestBody MemberVO mvo, Model m) {
		int isOk = msv.checkId(mvo.getId());
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(req, res, authentication);

	}

}
