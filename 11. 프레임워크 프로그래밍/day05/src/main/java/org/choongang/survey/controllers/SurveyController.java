package org.choongang.survey.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j // 로그 출력
@Controller
@RequestMapping("/survey")
@SessionAttributes("requestSurvey") // 세션에 임시로 값을 담음 // 여러페이지 구성에 많이 사용
public class SurveyController {
    // 여러개의 페이지로 구성된 양식

    @ModelAttribute // 세션에 임시로 값을 담음 // 모델에 속성 추가해줌 // 비어있는 속성이라고라...
    public RequestSurvey requestSurvey() {
        return new RequestSurvey();
    }

    @GetMapping("/step1")
    public String step1() {
        return "survey/step1";
    }

    @PostMapping("/step2")
    public String step2(RequestSurvey form, @SessionAttribute("requestSurvey") RequestSurvey  form2) {
    // @SessionAttribute("requsetSurvey") RequestSurvey  form2 : 세션쪽에서 가져온 값 // 질문1번과 질문2번의 값이 유지 될 거임

        log.info("form : " + form.toString());
        log.info("form2 : " + form2.toString());

        return "survey/step2";
    }

    @PostMapping("/step3")
    public String step3(RequestSurvey form, @SessionAttribute("requestSurvey") RequestSurvey  form2,  SessionStatus status, HttpSession session) {

        // HttpSeesion seesion // 세션 비운 후 세션 확인 하기 위해 씀
        log.info("form : " + form.toString());
        log.info("form2 : " + form2.toString());

        status.setComplete(); // 세션 비우기 - requestSurvey 세션 비우기

        System.out.println("세션 비우기 후: " + session.getAttribute("requestSurvey"));

        return "survey/step3";
    }
}
