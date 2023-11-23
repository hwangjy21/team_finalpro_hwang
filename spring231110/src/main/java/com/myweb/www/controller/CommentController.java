package com.myweb.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
	private CommentService csv;

	@Autowired
	public CommentController(CommentService csv) {
		this.csv = csv;
	}

	// 댓글 등록
	@PostMapping(value = "/postcmt", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {
		log.info("여기들어오는지 확인");
		int isOk = csv.addComment(cvo);
		log.info("cvo>>>> {}",cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	// 댓글 리스트 출력
	@GetMapping(value = "/list/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno") long bno,@PathVariable("page") int page,Principal principal) {
		
		String authId=principal.getName().toString();
		log.info(">>>>>>> bno / page >>> "+bno +"  "+page);
		PagingVO pgvo = new PagingVO(page,5); //qty=5
		PagingHandler list = csv.getList(bno,pgvo,authId);
		log.info(">>>>>>> ph List >>>>"+list);
		return new ResponseEntity<PagingHandler>(list, HttpStatus.OK);

	}

	// 댓글 삭제
	@DeleteMapping(value = "/remove/{cmtno}/{empid}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("cmtno") long cmtNo,@PathVariable("empid") String empId,Principal principal) {
		log.info("principal.getName {}",principal.getName().toString());
		log.info("테스트다1");
		log.info("cno >>{}",cmtNo);
		log.info("empId >>{}",empId);
		int isOk=0;
		if(principal.getName().toString().equals(empId)) {	
			isOk= csv.remove(cmtNo);
			log.info("테스트다2");
		}

		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);

	}
		
	//수정
	@PutMapping(value = "/modify", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody CommentVO cvo, Principal principal) {
	    log.info("프린시펄에서 따온 username>> {}", principal.getName().toString());
	    log.info("cvo{}", cvo);
	    log.info("cvo.getEmpId {}", cvo.getEmpId());
	    
	    int isOk = 0;
	    if(principal.getName().toString().equals(cvo.getEmpId())) {
	    	isOk = csv.modify(cvo);
	    }

	    return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
	            : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 좋아요
		@PostMapping(value="/commentLike/{cmtno}/{id}",produces = MediaType.TEXT_PLAIN_VALUE)
		public ResponseEntity<String> boardLike(@PathVariable("cmtno") long cmtNo, @PathVariable("id") String id){
			log.info("cmtNo>>>{}",cmtNo);
			log.info("id>>>{}",id);
			
			//체크되어있는지 안되어있는지 확인
			//1이면 이미 체크, 0이면 아닌거
			int check = csv.boardLikeCheck(cmtNo,id);
			log.info("체크되어있는지 안되어있는지 확인{}",check);
			
			if(check>0) { //이미 체크가 되어있으면
				//like취소
				csv.deleteBoardLike(cmtNo,id);
				
				return new ResponseEntity<String>("0",HttpStatus.OK);
			}else { //체크가 안되어있다면
				//like체크
				csv.addBoardLike(cmtNo,id);
				return new ResponseEntity<String>("1",HttpStatus.OK);
			}
			
		}
		
	//좋아요 수 구하기
		@GetMapping(value = "/commentLikeQty/{cmtno}", produces = MediaType.TEXT_PLAIN_VALUE)
		public ResponseEntity<String> cmtLikeQty(@PathVariable("cmtno") long cmtNo){
			int cmtLikeQty=csv.getCmtLikeQty(cmtNo);
			String strCmtLikeQty = String.valueOf(cmtLikeQty);
			
			return new ResponseEntity<String>(strCmtLikeQty,HttpStatus.OK);
		}

}
