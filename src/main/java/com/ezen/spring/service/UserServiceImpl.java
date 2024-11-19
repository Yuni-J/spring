package com.ezen.spring.service;

import com.ezen.spring.domain.UserVO;
import com.ezen.spring.repository.CommentMapper;
import com.ezen.spring.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UserVO> getList() {
        List<UserVO> userList = userMapper.getList();
        for(UserVO uvo : userList){
            uvo.setAuthList(userMapper.selectAuths(uvo.getEmail()));
        }
        return userList;
    }

    @Override
    public int modifyPwdEmpty(UserVO uvo) {
        return userMapper.modifyPwdEmpty(uvo);
    }

    @Override
    public int modify(UserVO uvo) {
        return userMapper.modify(uvo);
    }

    @Override
    public int remove(String email) {
        int isOk = userMapper.removeAuth(email);
        return userMapper.remove(email);
    }

    @Override
    public int updateLastLogin(String name) {
        return userMapper.updateLastLogin(name);
    }
}
