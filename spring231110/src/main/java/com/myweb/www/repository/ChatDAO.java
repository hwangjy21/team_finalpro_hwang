package com.myweb.www.repository;


import java.util.List;

import com.myweb.www.domain.ChatDTO;


import com.myweb.www.security.MemberVO;



public interface ChatDAO {

	int submit(ChatDTO cdto);

	List<ChatDTO> selectAll();


	List<MemberVO> selectEmpId(ChatDTO chatdto);

	List<MemberVO> selectEmp2(ChatDTO chatdto);

	int submitEmp2(ChatDTO chatDTO);


}