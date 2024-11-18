package com.ezen.spring.service;

import com.ezen.spring.Handler.PagingHandler;
import com.ezen.spring.domain.CommentVO;
import com.ezen.spring.domain.PagingVO;

public interface CommentService {
    int post(CommentVO commentVO);

    PagingHandler getList(long bno, PagingVO pgvo);

    int remove(long cno);

    int modify(CommentVO commentVO);
}
