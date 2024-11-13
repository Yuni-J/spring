package com.ezen.spring;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.repository.BoardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
class ApplicationTests {

	@Autowired
	private BoardMapper boardMapper;

	@Test
	void contextLoads() {
		for(int i=0; i<300; i++){
			BoardVO boardVO = BoardVO.builder()
					.title("test title "+i)
					.writer("tester"+((int)(Math.random()*100)+1))
					.content("test content "+i)
					.build();
			boardMapper.register(boardVO);
		}
	}

}
