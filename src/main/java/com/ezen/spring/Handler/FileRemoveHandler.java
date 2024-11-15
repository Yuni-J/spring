package com.ezen.spring.Handler;

import com.ezen.spring.domain.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
public class FileRemoveHandler {
    private final String BASE_PATH = "D:\\_mySpringProject\\_java\\_fileUpload";  // 이 경로를 동적으로 설정할 수 있으면 좋음

    public boolean deleteFile(FileVO fvo) {
        File delFile = new File(BASE_PATH, fvo.getSaveDir());
        boolean isDel = false;

        // 파일명 생성
        String delete = fvo.getUuid() + "_" + fvo.getFileName();

        try {
            // 원본 파일 경로
            File deleteFile = new File(delFile, delete);
            log.info(">>>> deleteFile > {}", deleteFile);

            // 원본 파일 삭제
            if (deleteFile.exists()) {
                isDel = deleteFile.delete();
                log.info(">>>> deleteFile deleted? {}", isDel);
            }

            // 파일이 이미지 타입일 경우 썸네일 파일 삭제
            if (fvo.getFileType() > 0) {  // 파일이 이미지일 경우
                String deleteThumb = fvo.getUuid() + "_th_" + fvo.getFileName();
                File deleteThumbFile = new File(delFile, deleteThumb);
                log.info(">>>> deleteThumbFile > {}", deleteThumbFile);

                // 썸네일 파일 삭제
                if (deleteThumbFile.exists()) {
                    boolean isThumbDel = deleteThumbFile.delete();
                    log.info(">>>> deleteThumbFile deleted? {}", isThumbDel);
                    isDel = isDel && isThumbDel; // 썸네일 파일 삭제 여부도 반영
                }
            }
        } catch (Exception e) {
            log.error("파일 삭제 중 오류 발생: ", e);
            throw new RuntimeException(e);
        }

        return isDel;
    }
}
