package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.ChatDTO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.ChatDAO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.FileDAO;

import com.myweb.www.security.MemberVO;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

	
	private ChatDAO chatdao;
	
	@Autowired
	public ChatServiceImpl(ChatDAO chatdao) {
		this.chatdao=chatdao;
	}
	
	
	@Override
	public int submit(ChatDTO chatDTO) {
		// TODO Auto-generated method stub

		log.info("ChatServiceImpl의 chatDTO>>> " + chatDTO);

		log.info("ChatServiceImpl의 submit() chatDTO>>> " + chatDTO);

		int isUp = chatdao.submit(chatDTO);
		return isUp;
	}


	@Override
	public List<ChatDTO> getList() {
		// TODO Auto-generated method stub
		return chatdao.selectAll();
	}



	@Override
	public List<MemberVO> list(ChatDTO chatdto) {
		// TODO Auto-generated method stub
		return chatdao.selectEmpId(chatdto);
	}


	@Override
	public List<MemberVO> list2(ChatDTO chatdto) {
		// TODO Auto-generated method stub
		return chatdao.selectEmp2(chatdto);
	}


	@Override
	public int submitEmp2(ChatDTO chatDTO) {
		// TODO Auto-generated method stub
		log.info("ChatServiceImpl의 submitEmp2() chatDTO>>> " + chatDTO);
		int isUp = chatdao.submitEmp2(chatDTO);
		return isUp;
	}


//	@Transactional
//	@Override
//	public List<BoardVO> getList() {
//		int isOk = bdao.updateCommentCount();
//		isOk = bdao.updateFileCount();
//		return bdao.selectAll();
//	}

}