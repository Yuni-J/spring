package com.ezen.spring.controller;

import com.ezen.spring.Handler.PagingHandler;
import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String list(Model m, PagingVO pgvo){
        //전체 게시글 수 가져우기
        int totalCount = bsv.getTotalCount(pgvo);
        PagingHandler ph = new PagingHandler(pgvo, totalCount);

        List<BoardVO> list = bsv.getList(pgvo);
        m.addAttribute("list", list);

        m.addAttribute("ph", ph);

        return "/board/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("bno") long bno, Model m){
        log.info(">>> detail bno {}", bno);
        BoardVO boardVO = bsv.getDetail(bno);
        m.addAttribute("bvo", boardVO);
        return "/board/detail";
    }

    @PostMapping("/modify")
    public String modify(BoardVO bvo, RedirectAttributes redirectAttributes){
        int isOk = bsv.update(bvo);
        redirectAttributes.addAttribute("bno", bvo.getBno());

        return "redirect:/board/detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bno") long bno){
        int isOk = bsv.delete(bno);
        return  "redirect:/board/list";
    }

}
