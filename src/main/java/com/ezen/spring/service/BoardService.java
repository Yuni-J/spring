package com.ezen.spring.service;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.PagingVO;

import java.util.List;

public interface BoardService {
    int register(BoardVO boardVO);

    List<BoardVO> getList(PagingVO pgvo);

    BoardVO getDetail(long bno);

    int update(BoardVO bvo);

    int delete(long bno);

    int getTotalCount(PagingVO pgvo);
}
