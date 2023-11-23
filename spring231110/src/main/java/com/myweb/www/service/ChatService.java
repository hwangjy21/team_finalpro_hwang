package com.myweb.www.service;


import java.util.List;

import com.myweb.www.domain.ChatDTO;

import com.myweb.www.security.MemberVO;


public interface ChatService {

	int submit(ChatDTO chatDTO);

	List<ChatDTO> getList();


	List<MemberVO> list(ChatDTO chatdto);

	List<MemberVO> list2(ChatDTO chatdto);

	int submitEmp2(ChatDTO chatdto);

}