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
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasenames("messages.commons", "messages.validations", "messages.errors"); // 클래스 패스가 기준
        ms.setDefaultEncoding("UTF-8");
        ms.setUseCodeAsDefaultMessage(true); // 메세지 코드가 없는 경우 코드로 메세지 대체

        return ms;
    }
}
