package com.ezen.spring.controller;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Controller
public class BoardController {
    private final BoardService bsv;

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String resigter(BoardVO boardVO){
        log.info(">>>>> boardVO {}", boardVO);
        int isOk = bsv.register(boardVO);
        return "index";
    }

    @GetMapping("/list")
    public String list(Model m){
        List<BoardVO> list = bsv.getList();
        log.info(">>>>> list {}", list);
        m.addAttribute("list", list);
        return "/board/list";
    }


}
