package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class) // "SpringJUnit4ClassRunner.class"가 실행되게 하는 거
@ContextConfiguration(classes = { com.myweb.www.config.RootConfig.class })
public class BoardTest {

	@Inject
	private BoardDAO bdao;

	@Test
	public void insertBoard() {
		for (int i = 0; i < 300; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setEmpNo(1);
			bvo.setId("1");
			bvo.setTitle("test title" + i);
			bvo.setContent("test content" + i);
			bvo.setBoardType("club");
			bvo.setClubCd("cl001");
			bdao.insert(bvo);
		}
	}
}
