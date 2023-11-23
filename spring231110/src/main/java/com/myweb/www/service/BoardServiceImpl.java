package com.myweb.www.service;

//import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.FileDAO;
import com.myweb.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	private BoardDAO bdao;
	private MemberDAO mdao;
//	private CommentDAO cdao;	 
//	private FileDAO fdao;
//	@Inject 
//	private CommentService csv;

	@Autowired
	public BoardServiceImpl(BoardDAO bdao, MemberDAO mdao) {
		this.bdao = bdao;
		this.mdao = mdao;

	}

	// 게시글 등록
	@Override
	public void write(BoardVO bvo) {
		
		// 타입에 따라 코드 set
		if (bvo.getBoardType().equals("depart")) {
			String depCd = mdao.selectDepCd(bvo.getId());
			bvo.setDepCd(depCd);
			log.info("depCd >> {}", depCd);
		} else if (bvo.getBoardType().equals("club")) {
			String clubCd = mdao.selecClubCd(bvo.getId());
			bvo.setClubCd(clubCd);
			log.info("clubCd >> {}", clubCd);
		}

			bdao.insert(bvo);

	}

	//로그인한 유저의 부서코드 가져오기
	@Override
	public String getUserDepCd(String id) {
		return mdao.selectDepCd(id);
	}

	//로그인한 유저의 부서게시글 가져오기
	@Override
	public List<BoardVO> getDepartList(String depCd,PagingVO pgvo) {
		return bdao.selectAllDepartBoard(depCd,pgvo);
	}

	//부서명 가져오기
	@Override
	public String getUserDepNm(String depCd) {
		return bdao.getUserDepNm(depCd);
	}

	//로그인한 유저의 동호회코드 가져오기
	@Override
	public String getUserClubCd(String id) {
		return mdao.selecClubCd(id);
	}

	//동호회명 가져오기
	@Override
	public String getUserClubNm(String clubCd) {
		return bdao.getUserClubNm(clubCd);
	}

	//동호회 리스트 가져오기
	@Override
	public List<BoardVO> getClubList(String clubCd,PagingVO pgvo) {
		return bdao.selectAllClubBoard(clubCd,pgvo);
	}

	//익명게시글 가져오기
	@Override
	public List<BoardVO> getAnonyList() {
		return bdao.selectAllAnonyBoard();
	}

	//디테일 bvo가져오기
	@Override
	public BoardVO getBoardDetail(long bno) {
		return bdao.selectOne(bno);
	}

	//부서게시글totalCount
	@Override
	public int getDepartTotalCount(String depCd,PagingVO pgvo) {
		return bdao.departTotalCount(depCd,pgvo);
	}

	//동호회게시글totalCount
	@Override
	public int getClubTotalCount(String clubCd, PagingVO pgvo) {
		return bdao.clubTotalCount(clubCd,pgvo);
	}

	

	

}
