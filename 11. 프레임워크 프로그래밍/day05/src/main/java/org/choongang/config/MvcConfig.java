package org.choongang.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.*;

@Configuration // 웹에대한 설정클래스 라는걸 알려주는 애노테이션
@EnableWebMvc // 애가 있어서 HandlerMapping, 핸들러어댑터, 뷰리졸브 와 같은 애들 따로 정의하지 않아도 이미 내부적으로 하고 있음 애 안쓰면 따로 웹앱?에 설정 추가해줘야함
@ComponentScan("org.choongang") // 애를 포함한 하위모두 스캔
@Import(DBConfig.class) // 설정클래스 더 있으면 배열 형태로 추가해주면 됨
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/templates/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") // 처음엔 컨트롤러 빈을 찾고 못찾으면 여기로 넘어가게끔 설정한거
        .addResourceLocations("classpath:/static");
    }
}
