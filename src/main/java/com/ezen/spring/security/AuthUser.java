package com.ezen.spring.security;

import com.ezen.spring.domain.UserVO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class AuthUser extends User {
    private UserVO userVO;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthUser(UserVO userVO){
        super(userVO.getEmail(), userVO.getPwd(),
                userVO.getAuthList().stream()
                        .map(authVO -> new SimpleGrantedAuthority(authVO.getAuth()))
                        .collect(Collectors.toList())
        );
        this.userVO = userVO;

    }
}
