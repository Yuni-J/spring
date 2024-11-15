package com.ezen.spring.controller;

import com.ezen.spring.Handler.FileHandler;
import com.ezen.spring.Handler.FileRemoveHandler;
import com.ezen.spring.Handler.PagingHandler;
import com.ezen.spring.domain.BoardDTO;
import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.FileVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Controller
public class BoardController {
    private final BoardService bsv;
    private final FileHandler fh;

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String resigter(BoardVO boardVO,
                           @RequestParam(name="files", required = false)MultipartFile[] files){
        log.info(">>>>> boardVO {}", boardVO);
        List<FileVO> flist = null;
        if(files != null && files[0].getSize() > 0){
            flist = fh.uploadFiles(files);
            log.info(">>>>>> flist >>> {}", flist);
        }
        int isOk = bsv.register(new BoardDTO(boardVO, flist));
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
        BoardDTO bdto = bsv.getDetail(bno);
        m.addAttribute("bdto", bdto);
        return "/board/detail";
    }

    @PostMapping("/modify")
    public String modify(BoardVO bvo, RedirectAttributes redirectAttributes,
                         @RequestParam(value = "files", required = false)MultipartFile[] files){
        List<FileVO> flist = null;
        if(files[0].getSize() > 0){
            flist = fh.uploadFiles(files);
        }
        int isOk = bsv.update(new BoardDTO(bvo, flist));
        redirectAttributes.addAttribute("bno", bvo.getBno());

        return "redirect:/board/detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bno") long bno){
        int isOk = bsv.delete(bno);
        return  "redirect:/board/list";
    }

    @ResponseBody
    @DeleteMapping("/file/{uuid}")
    public String removeFile(@PathVariable("uuid") String uuid){
        FileVO fvo = bsv.getFile(uuid);
        int isOk = bsv.removeFile(uuid);
        // 파일 삭제
        FileRemoveHandler fr = new FileRemoveHandler();
        boolean isDel = fr.deleteFile(fvo);
        return (isOk > 0 && isDel) ? "1" : "0";
    }

}
