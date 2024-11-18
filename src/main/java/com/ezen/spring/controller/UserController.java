package com.ezen.spring.controller;

import com.ezen.spring.domain.UserVO;
import com.ezen.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService usv;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signup(){
        return "/user/register";
    }

    @PostMapping("/signup")
    public String signup(UserVO userVO){
        userVO.setPwd(passwordEncoder.encode(userVO.getPwd()));
        int isOk = usv.insert(userVO);
        return "/index";
    }

    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }

//    @PostMapping("/login")
//    public String login(HttpServletRequest request, RedirectAttributes re){
//        log.info("");
//    }


}
