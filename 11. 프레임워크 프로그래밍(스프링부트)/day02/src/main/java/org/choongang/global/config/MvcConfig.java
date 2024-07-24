package org.choongang.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableJpaRepositories
@Configuration
@EnableJpaAuditing // 이벤트리스너관련 설정추가 애노테이션 // 불필요한 자원소비 방지위한 애노테이션
public class MvcConfig implements WebMvcConfigurer {
}
