package com.myweb.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.FileDAO;
import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.AuthMember;
import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	@Inject 
	private MemberDAO mdao;
	
	@Inject 
	private FileDAO fdao;

	@Transactional
	@Override
	public int register(MemberVO mvo) {
		//기존 메서드 활용
		int isOk = mdao.insertMember(mvo); //bno 등록
		return mdao.insertAuthInit(mvo.getId());
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		// TODO Auto-generated method stub
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public List<MemberVO> getList(PagingVO pagingVO) {
		// TODO Auto-generated method stub

		return mdao.selectAll(pagingVO);
		
	}

	@Transactional
	@Override
	public AuthMember detail(String id) {
		// TODO Auto-generated method stub
		List<AuthVO> testAutVoList = new ArrayList<>();
		AuthVO initialAuth = new AuthVO("temptest.test.test", "tempadminauth"); // 임시이메일 주소와 권한을 설정
		testAutVoList.add(initialAuth);

		MemberVO mvo = new MemberVO(0, id, id, id, id, id, id, id, id,id, testAutVoList ); //여기까지 임시로 지나칠려는 의도

		AuthMember amdto = new AuthMember(mvo); //새로 넣어줄려는 의도
		amdto.setMvo(mdao.selectOne(id));	//bdao bvo호출 select * from board where bno=#{bno}
		log.info(">>>>> amdto >> "+amdto.toString());
		return amdto;
	}
//	@Transactional
//	@Override
//	public BoardDTO detail2(long bno) {
//		bdao.readCount(bno);
//		BoardDTO bdto = new BoardDTO();
//		bdto.setBvo(bdao.selectOne(bno));	//bdao bvo호출 select * from board where bno=#{bno}
//		
//		bdto.setFlist(fdao.getFileList(bno));	//bdao bvo호출
//
////      DTO클래스는 아래와 같음
////		public class BoardDTO {
////			private BoardVO bvo;
////			private List<FileVO> flist;
////			
////		}
//
//		return bdto;
//	}


	@Override
	public int modify(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.modify(mvo);
	}

	@Override
	public int modifyPwdEmpty(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.modifyPwdEmpty(mvo);
	}
	
	
	@Override
	public int remove(String email) {
			mdao.removeAuth(email);
		return mdao.remove(email);
	}

	@Override
	public MemberVO detail2(String email) {
		// TODO Auto-generated method stub
		return mdao.selectOne2(email);
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return mdao.getTotalCount(pagingVO);
	}

	@Override
	public MemberVO getMemberDetail(String id) {
		return mdao.selectOne(id);	
	}

	@Override
	public int updatePw(String id, String password) {
		return mdao.updatePw(id, password);
	}

	@Override
	public int checkId(String id) {
		String registerId = mdao.checkId(id);
		if(registerId == null) {
			return 1;
		}else {
			return 0;			
		}
	}

	@Override
	public int insert(long empNo, List<FileVO> flist) {
		return fdao.insertProfile(empNo, flist.get(0));
	}

	@Override
	public long getMaxEmpNo() {
		return mdao.getMaxEmpNo();
	}

	
}
