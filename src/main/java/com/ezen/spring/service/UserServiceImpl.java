package com.ezen.spring.service;

import com.ezen.spring.domain.UserVO;
import com.ezen.spring.repository.CommentMapper;
import com.ezen.spring.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public int insert(UserVO userVO) {
        int isOk = userMapper.insert(userVO);
        if(isOk > 0){
            userMapper.authInsert(userVO.getEmail());
        }
        return isOk;
    }
}
