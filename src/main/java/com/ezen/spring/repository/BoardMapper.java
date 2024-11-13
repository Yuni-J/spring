package com.ezen.spring.repository;

import com.ezen.spring.domain.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int register(BoardVO boardVO);

    List<BoardVO> getList();
}
