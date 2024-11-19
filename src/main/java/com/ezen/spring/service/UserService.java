package com.ezen.spring.service;

import com.ezen.spring.domain.UserVO;

import java.util.List;

public interface UserService {
    int insert(UserVO userVO);

    List<UserVO> getList();

    int modifyPwdEmpty(UserVO uvo);

    int modify(UserVO uvo);

    int remove(String email);

    int updateLastLogin(String name);
}
