package com.ezen.spring.repository;

import com.ezen.spring.domain.CommentVO;
import com.ezen.spring.domain.PagingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface CommentMapper {
    int post(CommentVO commentVO);

    int getTotalCount(long bno);

    List<CommentVO> getList(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

    int remove(long cno);

    int modify(CommentVO commentVO);
}
