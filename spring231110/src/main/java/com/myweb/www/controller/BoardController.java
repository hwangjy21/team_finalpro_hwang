package com.myweb.www.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;

import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
// 폴더명 : board / mapping : board
	// mpapping => /board/register
	// 목적지 => /board/register

	private BoardService bsv;

 
	private FileHandler fh;

	
//	private CommentService csv;
	
	@Autowired
	public BoardController(BoardService bsv) {
		this.bsv = bsv;
	}

	// board/register jsp로 이동
	@GetMapping("/register")
	public String register() {// jsp에서 온 매핑이랑 뷰로 들어가는 매핑이 같아서(이름이 같아서) void로 하면 왔던 곳으로 가라고 할 수 있음
		return "/board/register"; // 이렇게 해도 됨(뷰로 들어가는 매핑)
	}

	
	// 게시글 등록
	@PostMapping("/register")
	public String postRegister(BoardVO bvo) {
		log.info("작성한 bvo >>{} ",bvo);
		bsv.write(bvo);
		return "/index";
	}
	
	//부서 게시판 리스트
	@GetMapping("departBoardList")
	public String getDepartList(Principal principal,Model model,PagingVO pgvo) {
		String id = principal.getName().toString();
		String depCd = bsv.getUserDepCd(id); //로그인한 id로 부서코드 가져오기
		String depNm = bsv.getUserDepNm(depCd); //부서명 가져오기
		
		List<BoardVO> departBoardList = bsv.getDepartList(depCd,pgvo); //가져온 부서의 게시판 가져오기
		int totalCount = bsv.getDepartTotalCount(depCd,pgvo);		
		PagingHandler ph = new PagingHandler(totalCount,pgvo);
		
		model.addAttribute("departBoardList", departBoardList);
		model.addAttribute("depNm", depNm);
		model.addAttribute("ph",ph);
		return "/board/departBoardList";
	}
	//동호회 게시판 리스트
	@GetMapping("clubBoardList")
	public String getClubList(Principal principal,Model model,PagingVO pgvo) {
		String id = principal.getName().toString();
		String clubCd = bsv.getUserClubCd(id); //로그인한 id로 동호회코드 가져오기
		String clubNm = bsv.getUserClubNm(clubCd); //동호회명 가져오기		
		log.info("clubCd>> {}",clubCd);
		
		List<BoardVO> clubBoardList = bsv.getClubList(clubCd,pgvo); //가져온 동호회의 게시판 가져오기
		log.info("clubBoardList>> {}",clubBoardList);
		for(BoardVO bvo : clubBoardList) {
			log.info(bvo.getClubCd());
		}
		int totalCount = bsv.getClubTotalCount(clubCd,pgvo);
		log.info("totalCount>> {}",totalCount);
		PagingHandler ph = new PagingHandler(totalCount,pgvo);
		log.info("ph>> {}",ph);
		
		model.addAttribute("clubBoardList", clubBoardList);
		model.addAttribute("clubNm", clubNm);
		model.addAttribute("ph", ph);
		return "/board/clubBoardList";
	}
		
	//익명 게시판 리스트(나중에...)
	@GetMapping("anonymousBoardList")
	public String getAnonyList(Model model) {
		List<BoardVO> anonyBoardList = bsv.getAnonyList();
		return "/board/anonymousBoardList";
	}
	
	@GetMapping("boardDetail")
	public String getDetail(@RequestParam("bno") long bno,Model model) {
		BoardVO bvo = bsv.getBoardDetail(bno);
		model.addAttribute("bvo",bvo);
		
		return "/board/detail";
	}
	
	
	// 삭제
	@GetMapping("/remove")
	public String remove(
			              @RequestParam("bno") long bno,
			              RedirectAttributes red 
							, Principal principal) {
		
		log.info("컨트롤러 겟방식 /remove진입");
		
		log.info("프린시펄"+principal);
		log.info(">>>> remove bno >> " + bno);
//		log.info("bvo.getWriter()는 "+principal.getName() +"       bvo.getWriter()는"+ bvo.getWriter() );
		
		BoardVO bvo = bsv.SelectOneForModify(bno);
		 
		if(Objects.equals(principal.getName(), bvo.getWriter())) {
		int reisOk = bsv.remove(bno);
		
		red.addFlashAttribute("reisOk", reisOk);
		}else {
		 red.addFlashAttribute("errorMessage", "현재접속자와 글 작성자가 일치하지 않습니다. 글 삭제 불가, 억지로 지우려마세요!");
		}
		return "redirect:/board/list";
		
	}
	
	@DeleteMapping(value="/file/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid ){
		log.info(">>> uuid >>" + uuid);
		int isOk = -99;
			isOk = bsv.removefile(uuid);
			log.info("isOk는 "+ isOk);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK): 
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);  //아니면 스트링값을 0 주고 서버에러값 넣어줌
	
	}
	
}
