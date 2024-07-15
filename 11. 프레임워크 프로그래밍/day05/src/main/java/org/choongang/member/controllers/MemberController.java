package org.choongang.member.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.member.entities.Member;
import org.choongang.member.services.JoinService;
import org.choongang.member.services.LoginService;
import org.choongang.member.validators.JoinValidator;
import org.choongang.member.validators.LoginValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로거 형태로 출력
@Controller
@RequestMapping("/member") // 공통적인 주소설정 할 때 많이 사용
@RequiredArgsConstructor // 의존성 주입(생성자 매개변수로 생성자 주입 , 이거 안쓰고 오토와이얼드 서도 ㅇㅋㅇㅋ)
public class MemberController {

    private final JoinValidator joinValidator;
    private final JoinService joinService;

    private final LoginValidator loginValidator;
    private final LoginService loginService;

    @GetMapping("/join") // @ModelAttribute RequestJoin form : 자료형이 이퀘스트 조인인 속성이 추가된다? 속성명 : requestJoin
    public String join(@ModelAttribute RequestJoin form){ // 회원가입 양식
        return "member/join";
    }

    @PostMapping("/join") // (RequestJoin form : 커맨드 객체 = 자동으로 내부적으로 el식 속성으로도 추가해줌, 속성명 : requestJoin
    public String joinPs(@Valid RequestJoin form, Errors errors){
        // 회원 가입 데이터 검증
        joinValidator.validate(form, errors);

        if(errors.hasErrors()){ // reject, rejectValue가 한번이라도 호출이되면(검증 실패) true // 밸리데이터 실패시 양식 여기에 정의하면 됨
            return "member/join";
        }

        joinService.process(form); // 회원 가입 처리

        return "redirect:/member/login"; // 스프링은 알아서 리다이렉트쓰면 요청헤더 통해 로케이션 헤더 설정해줌 = 사이트 찐 이동
    }

    @GetMapping("/login")
    public String login(@ModelAttribute RequestLogin form,
                        @CookieValue(name="savedEmail", required = false) String savedEmail
                        /*
                        @SessionAttribute(value = "member", required = false)Member member
                        */) {
        // ModelAttribute : get방식 일 때도 커맨드 객체 유지하기 위해서?
        // ModelAttribute : 세션 쪽에 있는 데이터를 가져와서 공통 데이터 유지?

        /*
        if(member != null) {
            log.info(member.toString()); // 출력 sout이랑 비슷
        }
         */

        if (savedEmail != null) {
            form.setSaveEmail(true);
            form.setEmail(savedEmail);
        }

        return "member/login";
    }

/* 세션 올드 버전
    @GetMapping("/login")
    public String login(@ModelAttribute RequestLogin form, HttpSession session) {
        // ModelAttribute : get방식 일 때도 커맨드 객체 유지하기 위해서?
        // ModelAttribute : 세션 쪽에 있는 데이터를 가져와서 공통 데이터 유지?

        Member member = (Member) session.getAttribute("member");
        if(member != null) {
            log.info(member.toString()); // 출력 sout이랑 비슷
        }

        return "member/login";
    }
 */

    @PostMapping("/login")
    public String loginPs(@Valid RequestLogin form, Errors errors){ //@Valid : 이 커맨드 객체가 검증대상이라는 걸 알려줌
        // 로그인 데이터 검증
        loginValidator.validate(form, errors);

        if(errors.hasErrors()){ // reject, rejectValue가 한번이라도 호출이되면(검증 실패) true // 밸리데이터 실패시 양식 여기에 정의하면 됨
            return "member/login";
        }

        // 로그인 처리 - 서비스
        //String email = form.getEmail();
        //loginService.process(email);
        loginService.process(form);


        return "redirect:/";
    }

    @RequestMapping("/logout") // 겟이든 포스트이든 다 유입될 수 있게함
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 비우기

        return "redirect:/member/login";
    }

    /*
    @InitBinder // 특정 컨트롤러에서 사용할 공통적인 Valdiator
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(joinValidator);
    }
     */
}
