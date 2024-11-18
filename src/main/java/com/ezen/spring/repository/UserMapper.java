package com.ezen.spring.repository;

import com.ezen.spring.domain.AuthVO;
import com.ezen.spring.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(UserVO userVO);

    int authInsert(String email);

    UserVO selectEmail(String username);

    List<AuthVO> selectAuths(String username);
}
