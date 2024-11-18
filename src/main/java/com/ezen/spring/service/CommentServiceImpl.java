package com.ezen.spring.service;

import com.ezen.spring.Handler.PagingHandler;
import com.ezen.spring.domain.CommentVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentMapper commentMapper;

    @Override
    public int post(CommentVO commentVO) {
        return commentMapper.post(commentVO);
    }

    @Override
    @Transactional
    public PagingHandler getList(long bno, PagingVO pgvo) {
        int totalCount = commentMapper.getTotalCount(bno);
        List<CommentVO> cmtList = commentMapper.getList(bno, pgvo);
        PagingHandler ph = new PagingHandler(pgvo, totalCount, cmtList);
        return ph;
    }

    @Override
    public int remove(long cno) {
        return commentMapper.remove(cno);
    }

    @Override
    public int modify(CommentVO commentVO) {
        return commentMapper.modify(commentVO);
    }
}
