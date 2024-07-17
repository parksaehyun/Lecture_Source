package org.choongang.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import org.choongang.member.validators.JoinValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration // 웹에대한 설정클래스 라는걸 알려주는 애노테이션
@EnableWebMvc // 애가 있어서 HandlerMapping, 핸들러어댑터, 뷰리졸브 와 같은 애들 따로 정의하지 않아도 이미 내부적으로 하고 있음 애 안쓰면 따로 웹앱?에 설정 추가해줘야함
@ComponentScan("org.choongang") // 애를 포함한 하위모두 스캔
@Import({DBConfig.class, MessageConfig.class, InterceptorConfig.class, FileConfig.class}) // 설정클래스 더 있으면 배열 형태로 추가해주면 됨
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
    // implements WebMvcConfigurer : 인터페이스 모든 설정이 다 들어가 있음 중요!!!

    private final JoinValidator joinValidator; // 지금은 맞지 않는 상황이지만 보여주기 위해 그냥 적용함

    /*
    @Override
    public Validator getValidator() { // 모든 컨트롤러에 적용 될 수 있는 전역 Validator
        return joinValidator;
    }
    */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/templates/", ".jsp");// 출력 결과물이 버퍼에 담긴다
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 처음엔 컨트롤러 빈을 찾고 못찾으면 여기로 넘어가게끔 설정한거
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") // 처음엔 컨트롤러 빈을 찾고 못찾으면 여기로 넘어가게끔 설정한거의 경로 설정
        .addResourceLocations("classpath:/static");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) { // 컨트롤러 없이 뷰만 연동하기 위해서 사용
        registry.addViewController("/").setViewName("main/index");

        registry.addViewController("/mypage").setViewName("mypage/index");
    }

    @Bean //  파일경로 설정
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        // static 형태로 정의 이유 : 뭐더라
        // Placeholder :  ${} 로 교체의 방식
        // @Value 애노테이션을 가지고 설정 적용
        String fileName = "application";
        String profile = System.getenv("spring.profiles.active"); // 환경 변수
        fileName += StringUtils.hasText(profile) ? "-" + profile : "";

        PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
        //conf.setLocations(new ClassPathResource("application.properties")); // 설정파일의 위치 알려주기
        conf.setLocations(new ClassPathResource(fileName + ".properties")); // 설정파일의 위치 알려주기

        return conf;
    }

    @Override // json 날짜 형식 설정
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
                //.xml()  // 응답하는 형식 : xml로 응답한다
                .json() // 응답하는 형식 : json으로 응답한다
                .serializerByType(LocalDateTimeSerializer.class, new LocalDateTimeSerializer(formatter))
                .build();

        converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
        // 그냥 add하면 우선순위가 떨어져서 0번째라고 해주어야 함
    }
}
