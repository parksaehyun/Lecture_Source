package org.choongang.global.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class MemberOnlyInterceptor implements HandlerInterceptor { // 회원전용 페이지
    // 디폴트 메서드라 필요한것만 오버라이딩 하면 됨

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info(" preHandle() 유입!"); // 공통처리

        // 통제
        // 세션값이 있냐없냐 여부로 로그인여부 쳌(라이크 플젝때 memberutil클래스)
        // 로그인이 아닐때는 페이지가 보이지 않게 처리
        HttpSession session = request.getSession();
        if (session.getAttribute("member") != null) {
            // 로그인 상태인 경우
            return true; // 컨트롤러 빈 메서드 실행
        }

        response.sendRedirect(request.getContextPath() + "/member/login");

        return false; // 미로그인 상태
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 시점 : 모델엔뷰 반환 직 후 실행되는 인터셉트
        // 그래서 ModelAndView가 매개변수로 있음

        // 속성 값 뷰페이지에 넘기기
        // "message" : el속성으로 바로 접근 가능
        modelAndView.addObject("message", "postHandle!");

        log.info(" postHandle() 유입!");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         // 시점 : 응답 후에 실행
        // 오류x : Exception 값이 null
        // 오류 o : Exception여기에 담김

        log.info(" afterCompletion() 유입!");
    }
}
