package org.choongang.file.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {
    // 겟방식 : 양식 보여주기
    // 포스트 : 처리 형태로 구성할거임

    @Value("${file.upload.path}") // // @Value애노테이션을 가지고 설정 적용
    private String uploadDir;

    @GetMapping("/upload")
    public String upload() {

        return "file/upload";
    }

    @ResponseBody // 반환값 없게 할려구 // 반환값 : 템플릿 경로
    @PostMapping("/upload")
    public void uploadPs(@RequestPart("file") MultipartFile file) {
        // @RequestPart : 어떤 이름으로 파일이 넘어오는지 알려주기, 애가 바이너리형태로 받아오는거
        // @RequestPart : @RequestParam 이랑 비슷한거 애는 양식 형태로 받아오는거



        String fileName = file.getOriginalFilename(); // 파일 이름 확인
        log.info("파일명 : {}" ,fileName);

        File uploadPath = new File( uploadDir + fileName);

        try {
            file.transferTo(uploadPath);
        } catch (IOException e) {}
    }
}
