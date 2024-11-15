package com.ezen.spring.repository;

import com.ezen.spring.domain.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    int insertFile(FileVO fvo);

    List<FileVO> getFileList(long bno);

    int removeFile(String uuid);

    FileVO getFile(String uuid);
}
