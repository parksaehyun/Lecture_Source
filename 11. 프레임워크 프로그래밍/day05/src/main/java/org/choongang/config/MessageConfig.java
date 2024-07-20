package org.choongang.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class MessageConfig {
    // 설정 클래스 한곳에 몰아넣으면 관리가 힘드니 분리
    @Bean
    public MessageSource messageSource() {
        // MessageSource : 인터페이스
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource(); //MessageSource 인터페이스를 구현하는 클래스, 프로퍼티 파일로부터 메시지를 로드
        ms.setBasenames("messages.commons", "messages.validations", "messages.errors"); // 메시지 파일의 기본 이름을 설정// 클래스 패스가 기준
        ms.setDefaultEncoding("UTF-8"); // 메시지 파일의 기본 인코딩을 UTF-8로 설정
        ms.setUseCodeAsDefaultMessage(true); // 메세지 코드가 없는 경우 코드로 메세지 대체

        return ms; // ResourceBundleMessageSource 객체를 반환하여, 이 객체가 스프링 컨텍스트의 MessageSource 빈으로 사용되도록 함
    }
}
