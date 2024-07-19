package org.choongang.config;

import lombok.RequiredArgsConstructor;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@RequiredArgsConstructor
public class ThymeleafConfig implements WebMvcConfigurer {
    private final WebApplicationContext applicationContext;

    @Bean // SpringResourceTemplateResolver : 템플릿 경로 설정
    public SpringResourceTemplateResolver templateResolver() {
        // setPrefix, setSuffix 사이에 주소들어감
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates2/");
        templateResolver.setSuffix(".html"); // 보통 디자이너가 넘겨주는 파일이 html이 많아서 관례적으로 이걸 많이 씀 // 내츄럴 템플릿??
        templateResolver.setCacheable(false); // 캐시 : 폴스 - // 타임리프도 jsp와 비슷하게 번역기술임 = 번역된 새로운 파일을 만들어줌 = 서버가 일을 한다 = 그만큼 자원을 소비 했다 = 한번 번역된거는 거의 바뀔일이 없음 = 서버에 올라가면 그거 그냥 계속 쓰면됨 // 한번 번역된거 그대로 사용하자 = 캐시 폴스
        // 개발할때는 캐시 폴스로 해야함 = 캐시 트루로 하면 계속 서버 껏다 켜야지 번역이 새로되면서 내가 작성한 수정코드가 보임 / 귀찮으니 캐시 폴스하고 개발하자 = 매 요청시 마다 번역이 계속 된다
        // 배포시에는 트루로 바꾸어야 함
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true); // el식 허용
        templateEngine.addDialect(new Java8TimeDialect()); // 확장기능
        templateEngine.addDialect(new LayoutDialect()); // 확장기능
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setContentType("text/html"); // 템플릿쪽에 인코딩이런거 설정
        resolver.setCharacterEncoding("utf-8"); // <%@ page contentType="text/html; charset=UTF-8" %> 이렇게 맨날 했던거에서 해★방
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver()); // thymeleafViewResolver 설정반영
    }
}
