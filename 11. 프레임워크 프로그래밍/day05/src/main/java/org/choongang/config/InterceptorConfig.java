package org.choongang.config;

import lombok.RequiredArgsConstructor;
import org.choongang.global.interceptors.MemberOnlyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    // url가지고 인터셉트가 적용될 범위 설정하기
    // WebMvcConfigurer 이거 MvcConfig에서도 implements하는데 ㄱㅊ나?
    // 메서드별로 구분한다 생각하면 됨

    private final MemberOnlyInterceptor memberOnlyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 설정없는경우 // 모든 컨트롤러에 적용
        registry.addInterceptor(memberOnlyInterceptor)
                .addPathPatterns("/mypage/**");
        // addPathPatterns : 인터셉트 적용 범위 설정
        // ** 마이페이지 포함한 하위 모든 경로
        // * : 마이페이지만
        // ? : 문자 한개
    }


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) { // 설정없는경우 // 모든 컨트롤러에 적용
//        registry.addInterceptor(memberOnlyInterceptor);
//    }
}
