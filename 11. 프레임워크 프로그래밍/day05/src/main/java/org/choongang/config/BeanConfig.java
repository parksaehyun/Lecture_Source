package org.choongang.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 수동등록 빈 설정 클래스
public class BeanConfig {

    @Bean // 수동등록
    public ObjectMapper objectMapper() {
        // objectMapper : JSON 데이터를 Java 객체로 변환하거나 Java 객체를 JSON 데이터로 변환하는 데 사용
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        return om;
    }
}
