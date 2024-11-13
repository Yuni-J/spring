package com.ezen.spring.service;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    @Override
    public int register(BoardVO boardVO) {
        return boardMapper.register(boardVO);
    }

    @Override
    public List<BoardVO> getList() {
        return boardMapper.getList();
    }
}
