package org.choongang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // : 웹관련 설정할거다1
public class FileConfig implements WebMvcConfigurer { // implements WebMvcConfigurer : 웹관련 설정할거다2

    @Value("${file.upload.path}") // 설정 값 바로 주입 // // @Value애노테이션을 가지고 설정 적용
    private String uploadPath;

    @Override // 파일 업로드 정적 경로 설정
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")//.addResourceLocations("file:///D:/uploads/"); // /// 를 입력해야 //로 인식함
        .addResourceLocations("file:///" + uploadPath);
    }
}
