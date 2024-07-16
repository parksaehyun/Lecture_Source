package org.choongang.global.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.choongang.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice("org.choongang") // 공통적인 값 유지 및 처리할 때 사용 // "org.choongang" : value 통해 범위 설정, org.choonang 의 하위경로가 범위이다
public class CommonControllerAdvice {

    @ExceptionHandler(Exception.class) // 다형성 // 발생하는 예외 다 여기로 유입되게 함
    // Exception.class : 내부에서 발생한 예외??
    public ModelAndView errorHandler(Exception e, HttpServletRequest request, HttpServletResponse response, Model model) {
        // Exception e : 발생한 예외가 여기 담겨지게 됨
        e.printStackTrace();
        log.info("CommonControllerAdvice");

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 기본예외는 스프링이 제공해주는 이너상수 에러응답코드 500 으로 고정

        // 내가 정의하는 응답상태코드 설정??
        if(e instanceof CommonException commonException) { // instanceof : 출처 조회 // 내가 정의한 에러인지 쳌 //
            //CommonException commonException = (CommonException) e;  // 내가 정의하는 에러는 응답상태코드만 뽑아올거임
            status = commonException.getStatus(); // 내가 정의한 응답코드로 가져오기
        }

        // 응답코드 내보내기 : http서블릿 리스판스 통해서 응답코드 보내줄 수 있음
        // 스플링 제공하는 기능(응답코드 내보내기) : 리스판스 엔티티, 모델 엔 뷰
        ModelAndView modelAndView = new ModelAndView(); // 데이터와 템플릿 말구 상태코드도 설정 가능?

        modelAndView.setStatus(status); // 응답상태코드 // 응답헤더에서 응답상태코드를 가지고 응답
        modelAndView.setViewName("error/common");

        return modelAndView;
    }
}
