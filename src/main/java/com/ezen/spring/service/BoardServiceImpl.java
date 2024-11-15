package com.ezen.spring.service;

import com.ezen.spring.domain.BoardDTO;
import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.FileVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.repository.BoardMapper;
import com.ezen.spring.repository.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

    @Transactional
    @Override
    public int register(BoardDTO boardDTO) {
        int isOk = boardMapper.register(boardDTO.getBvo());
        if(boardDTO.getFlist() == null){
            return isOk;
        }
        if(isOk > 0 && boardDTO.getFlist().size() > 0){
            // 파일 저장
            // board의 bno 가져오기
            long bno = boardMapper.getBno();
            for(FileVO fvo : boardDTO.getFlist()){
                fvo.setBno(bno);
                isOk *= fileMapper.insertFile(fvo);
            }
        }
        return isOk;
    }


    @Override
    public List<BoardVO> getList(PagingVO pgvo) {
        return boardMapper.getList(pgvo);
    }

    @Override
    public BoardDTO getDetail(long bno) {
        // fileList 가져와서 DTO 생성
        BoardDTO bdto = new BoardDTO(boardMapper.getDetail(bno),
                fileMapper.getFileList(bno));

        log.info("bdto >> {}", bdto);
        return bdto;
    }

    @Override
    public int update(BoardDTO boardDTO) {
        int isOk = boardMapper.update(boardDTO.getBvo());
        if(boardDTO.getFlist() == null){
            return isOk;
        }
        if(isOk > 0 && boardDTO.getFlist().size() > 0){
            for(FileVO fvo : boardDTO.getFlist()){
                fvo.setBno(boardDTO.getBvo().getBno());
                isOk *= fileMapper.insertFile(fvo);
            }
        }
        return isOk;
    }

    @Override
    public int delete(long bno) {
        return boardMapper.delete(bno);
    }

    @Override
    public int getTotalCount(PagingVO pgvo) {
        return boardMapper.getTotalCount(pgvo);
    }

    @Override
    public int removeFile(String uuid) {
        return fileMapper.removeFile(uuid);
    }

    @Override
    public FileVO getFile(String uuid) {
        return fileMapper.getFile(uuid);
    }
}
