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

	@GetMapping("/test")
	public void test11() {}
	
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
	
	//글상세
	@GetMapping("boardDetail")
	public String getDetail(@RequestParam("bno") long bno,Model model,Principal principal) {
		
		String authId=principal.getName().toString();
		
		BoardVO bvo = bsv.getBoardDetail(bno,authId);
		log.info("bvo>>>>>{} ",bvo);
		
		if(!authId.equals(bvo.getId())) {
			bsv.updateReadQty(bno);
		}
		model.addAttribute("bvo",bvo);
		
		return "/board/detail";
	}
	
	//게시글 좋아요
	@PostMapping(value="/boardLike/{bno}/{id}",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> boardLike(@PathVariable("bno") long bno, @PathVariable("id") String id){
		log.info("bno>>>{}",bno);
		log.info("id>>>{}",id);
		
		//체크되어있는지 안되어있는지 확인
		//1이면 이미 체크, 0이면 아닌거
		int check = bsv.boardLikeCheck(bno,id);
		
		if(check>0) { //이미 체크가 되어있으면
			//like취소
			bsv.deleteBoardLike(bno,id);
			
			return new ResponseEntity<String>("0",HttpStatus.OK);
		}else { //체크가 안되어있다면
			//like체크
			bsv.addBoardLike(bno,id);
			return new ResponseEntity<String>("1",HttpStatus.OK);
		}
		
	}
	
	@GetMapping(value="/commentCount/{bno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> commentCount(@PathVariable("bno") long bno){
		int cmtCount=bsv.commentCount(bno);
		String strcmtCount=String.valueOf(cmtCount);
		log.info("strcmtCount>>>{}",strcmtCount);
		return new ResponseEntity<String>(strcmtCount,HttpStatus.OK);
	}
	
}
