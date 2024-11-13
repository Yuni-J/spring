package com.ezen.spring.service;

import com.ezen.spring.domain.BoardVO;

import java.util.List;

public interface BoardService {
    int register(BoardVO boardVO);

    List<BoardVO> getList();
}
