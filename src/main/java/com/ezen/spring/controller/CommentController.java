package com.ezen.spring.controller;

import com.ezen.spring.Handler.PagingHandler;
import com.ezen.spring.domain.CommentVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.service.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
    private final CommentService csv;

    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody CommentVO commentVO) {
        log.info(">>>>> commentVO >> {}", commentVO);
        int isOk = csv.post(commentVO);
        return isOk > 0? "1":"0";
    }

    @GetMapping("/list/{bno}/{page}")
    @ResponseBody
    public PagingHandler list(@PathVariable("bno") long bno, @PathVariable("page") int page){
        PagingVO pgvo = new PagingVO(page, 5);
        PagingHandler ph = csv.getList(bno, pgvo);
        log.info(">>> ph > {}", ph);
        return  ph;
    }

    @DeleteMapping("/remove/{cno}")
    @ResponseBody
    public String delete(@PathVariable("cno") long cno){
        int isOk = csv.remove(cno);
        return isOk>0?"1":"0";
    }

    @PutMapping("/modify")
    @ResponseBody
    public String modify(@RequestBody CommentVO commentVO){
        int isOk = csv.modify(commentVO);
        return isOk>0?"1":"0";
    }

}
