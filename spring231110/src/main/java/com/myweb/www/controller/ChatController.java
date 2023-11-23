package com.myweb.www.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.ChatDTO;
import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;

import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;


import com.myweb.www.security.AuthMember;
import com.myweb.www.security.MemberVO;


import com.myweb.www.service.BoardService;
import com.myweb.www.service.ChatService;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/chaturl/*")
@Controller
public class ChatController {
// 폴더명 : board / mapping : board
	// mpapping => /board/register
	// 목적지 => /board/register

	@Inject
	private ChatService chatsv;

//	private BoardService bsv;
//
//	private FileHandler fh;
//	
//	private CommentService csv;
	
	@Autowired
	public ChatController(ChatService chatsv) {
		log.info("ChatController입니당");
		this.chatsv = chatsv;
//		this.fh = fh;
	}

	// 익명 채팅글쓰기 jsp로 이동
	@GetMapping("/chat")
	public String register(Model model, Principal principal) {// jsp에서 온 매핑이랑 뷰로 들어가는 매핑이 같아서(이름이 같아서) void로 하면 왔던 곳으로 가라고 할 수 있음
		log.info("겟 /chat 진입");
		 // principal 객체에서 사용자 이름(ID)을 가져옴
	    String username = principal.getName();
	    // Model 객체에 사용자 이름(ID) 추가
	    model.addAttribute("username", username);

		return "/chatfolder/chat"; // 이렇게 해도 됨(뷰로 들어가는 매핑)
	}
	
	
	
	


	@PostMapping(value ="/chat" , consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public ResponseEntity<String> write(@RequestBody ChatDTO chatdto) 
	{
		log.info(">>>>>>chatdto>> "+chatdto.toString());
		int isOk = -888;	
		int isOk2 = -999;
		if(chatdto.getFromID() == null || chatdto.getFromID().equals("") 
//				|| chatdto.getToID() == null ||	chatdto.getToID().equals("")
				||chatdto.getChatContent().equals("")){
			isOk = 0;
		}else {
			isOk = chatsv.submit(chatdto);
		}

		log.info(">>컨트롤러 chatsv submit >>" + (isOk>0? "OK":"FAIL"));
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
						: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	// 채팅 리스트 출력
//	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<List> list(Model model) {
//		
//		log.info(">>>>GetMapping>>> /chat/ 진입  >>> ");
//		chatList = chatsv.getList();
////		log.info("chatlist의 값 "+ chatlist);
//		model.addAttribute("chatList" , chatList);
//		log.info("model의 값 "+ model);
//		
//		return new ResponseEntity<List>(chatList, HttpStatus.OK);
//
//	}
	
	// 채팅 리스트 출력
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ChatDTO>> list() { // Model 파라미터 제거, @ResponseBody 사용시 필요 없음
		
		log.info(">>>> GetMapping >>> /chat/list 진입 >>> ");
		List<ChatDTO> chatList = chatsv.getList(); // List<ChatDTO> 반환하는지 확인
	    log.info("chatList의 값 " + chatList);
	    
	    return new ResponseEntity<>(chatList, HttpStatus.OK); // 제네릭 파라미터 간소화
	}
	
	// 댓글 리스트 출력
//	@GetMapping(value = "/list/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<PagingHandler> list(@PathVariable("bno") long bno,@PathVariable("page") int page) {
//		log.info(">>>>>>> bno / page >>> "+bno +"  "+page);
//		PagingVO pgvo = new PagingVO(page,5); //qty=5
//		PagingHandler list = csv.getList(bno,pgvo);
//		log.info(">>>>>>> ph List >>>>"+list);
//		return new ResponseEntity<PagingHandler>(list, HttpStatus.OK);
//
//	}
	
//	// 리스트 출력
//	@GetMapping("/list")
//	public String list(Model model) {
//		List<BoardVO> list = bsv.getList();
//		model.addAttribute("list", list);
//
//		return "/board/list";
//	}



	

	// 채팅글쓰기 jsp로 이동
	@GetMapping("/find")
	public String finduser(Model model, Principal principal) {// jsp에서 온 매핑이랑 뷰로 들어가는 매핑이 같아서(이름이 같아서) void로 하면 왔던 곳으로 가라고 할 수 있음
		log.info("get매핑 /find 진입");
		 // principal 객체에서 사용자 이름(ID)을 가져옴
	    log.info("principal은 {}", principal.toString());
		String username = principal.getName();
		
		
		String empNm="";
		if (principal instanceof UsernamePasswordAuthenticationToken) {
		    UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
		    Object principalObj = token.getPrincipal();

		    if (principalObj instanceof AuthMember) {
		        AuthMember authMember = (AuthMember) principalObj;
		        empNm = authMember.getMvo().getEmpNm(); // AuthMember 클래스와 MemberVO 클래스에 적절한 getter 메서드가 정의되어 있어야 함

		        log.info("EmpNm은 {}", empNm);
		        // 이제 empNm 변수를 사용할 수 있습니다.
		    } else {
		        log.info("Principal 객체가 AuthMember 타입이 아닙니다.");
		    }
		} else {
		    log.info("Principal 객체가 UsernamePasswordAuthenticationToken 타입이 아닙니다.");
		}
		

	    
	    log.info("username는 "+username);
	    // Model 객체에 사용자 이름(ID) 추가
	    model.addAttribute("username", username);
	    model.addAttribute("empNm", empNm);
	    log.info("model은 "+ model);
	    log.info("/chatfolder/find 진입 직전");
	    
		return "/chatfolder/find"; // 이렇게 해도 됨(뷰로 들어가는 매핑)
	}
	
	
	
	@PostMapping(value ="/find" , consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<MemberVO>> find(@RequestBody ChatDTO chatdto) 
	{
		log.info("포스트 find 진입");
		log.info(">>>>>>chatdto>> "+chatdto.toString());
		int isOk = -888;	
		int isOk2 = -999;
		log.info("findList 초기화 직전");
		List<MemberVO> findList;
		log.info("if문 직전");
		if(chatdto.getToID() == null || chatdto.getToID().equals("") )
		{
			log.info("chatdto가 이상함");
			isOk = 0;
			findList = chatsv.list(chatdto); // 뭐 에러 날것 같기 한데 일단 진행...
		}else {
			log.info("chatdto가 멀쩡함");
			isOk = 1;
			
			findList = chatsv.list(chatdto);
			log.info("findList는^^ "+ findList);
		}

		log.info("어쩄든 findList는 "+ findList);
		log.info(">>컨트롤러 chatsv.list(chatdto) >>>" + (isOk>0? "OK":"FAIL"));
		log.info("isOk는" + isOk);
		return isOk > 0 ? new ResponseEntity<>(findList, HttpStatus.OK)
						: new ResponseEntity<>(findList, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
	
	
	
	
	
	
	

	// 서로 2명만 채팅글쓰기 jsp로 이동
	@GetMapping("/chat2")
	public String register2(Model model, Principal principal) {// jsp에서 온 매핑이랑 뷰로 들어가는 매핑이 같아서(이름이 같아서) void로 하면 왔던 곳으로 가라고 할 수 있음
		log.info("겟 /chat2 진입");
		 // principal 객체에서 사용자 이름(ID)을 가져옴
	    String username = principal.getName();
	    // Model 객체에 사용자 이름(ID) 추가
	    model.addAttribute("username", username);
		return "/chatfolder/chat2"; // 이렇게 해도 됨(뷰로 들어가는 매핑)
	}
	
	
	
	@PostMapping(value ="/chat2" , consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> write2(@RequestBody ChatDTO chatdto) 
	{
		log.info(">>>>>>chatdto>> "+chatdto.toString());
		int isOk = -777;	
		int isOk2 = -666;
		if(chatdto.getFromID() == null || chatdto.getFromID().equals("") 
//				|| chatdto.getToID() == null ||	chatdto.getToID().equals("")
				||chatdto.getChatContent().equals("")){
			isOk = 0;
		}else {
			isOk = chatsv.submitEmp2(chatdto);
		}

		log.info(">>컨트롤러 chatsv submit >>" + (isOk>0? "OK":"FAIL"));
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
						: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	

	
	
	//1:1 용 채팅 리스트만 출력하려고 함
	@PostMapping(value ="/list2" , consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<MemberVO>> list2(@RequestBody ChatDTO chatdto) 
	{
		log.info("포스트 list2 진입");
		log.info(">>>>>>chatdto>> "+chatdto.toString());
		int isOk = -777;	
		int isOk2 = -555;
		log.info("list2 초기화 직전");
		List<MemberVO> list2;
		log.info("if문 직전");
		if(chatdto.getToID() == null || chatdto.getToID().equals("") )
		{
			log.info("chatdto가 이상함");
			isOk = 0;
			list2 = chatsv.list2(chatdto); // 뭐 에러 날것 같기 한데 일단 진행...
		}else {
			log.info("chatdto가 멀쩡함");
			isOk = 1;
			
			list2 = chatsv.list(chatdto);
			log.info("list2는^^ "+ list2);
		}

		log.info("어쩄든 findList는 "+ list2);
		log.info(">>컨트롤러 chatsv.list(chatdto) >>>" + (isOk>0? "OK":"FAIL"));
		log.info("isOk는" + isOk);
		return isOk > 0 ? new ResponseEntity<>(list2, HttpStatus.OK)
						: new ResponseEntity<>(list2, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
	
	
	
}