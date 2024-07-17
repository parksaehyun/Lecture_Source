package org.choongang.member.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.global.exceptions.BadRequestException;
import org.choongang.member.entities.Member;
import org.choongang.member.services.JoinService;
import org.choongang.member.services.LoginService;
import org.choongang.member.validators.JoinValidator;
import org.choongang.member.validators.LoginValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j // 로거 형태로 출력
@Controller
@RequestMapping("/member") // 공통적인 주소설정 할 때 많이 사용
@RequiredArgsConstructor // 의존성 주입(생성자 매개변수로 생성자 주입 , 이거 안쓰고 오토와이얼드 서도 ㅇㅋㅇㅋ)
public class MemberController {

    private final JoinValidator joinValidator;
    private final JoinService joinService;

    private final LoginValidator loginValidator;
    private final LoginService loginService;

    @GetMapping("/join") // @ModelAttribute RequestJoin form : 자료형이 리퀘스트 조인인 속성이 추가된다? 속성명 : requestJoin
    public String join(@ModelAttribute RequestJoin form){ // 회원가입 양식
        return "member/join"; // 포워드(버퍼치환)
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
                        // @CookieValue : 쿠키에 있는 쿠키 가져오기?
                        // name : 쿠키이름
                        // required : 필수여부(트루 : 반드시 해당 쿠키가 존재해야만 함  = 없으면 400 오류 뜸)(펄스: 없을 때는 null값으로 넣어줌)
                        // String savedEmail : 쿠키?
                        //@CookieValue : 쿠키에 있는 쿠키 가져오기?
                        // @SessionAttribute : 세션에 있는 쿠키 가져오기
                        // 둘다 같은 기능을 한다 = 사용자 개인 서비스
                        /*
                        @SessionAttribute(value = "member", required = false)Member member
                        */) { // 세션에 있는 쿠키 가져오기방식 // 세션에 있는 쿠키 가져오기
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

    @GetMapping("/list")
    public String list(@Valid @ModelAttribute MemberSearch search, Errors errors) {

        log.info(search.toString()); // 커맨드객체로 제대로 유입되는지 확인할거

        boolean result = false; // 일부러 실습위해 예외발생시킴
        if (!result) {
            throw new BadRequestException("예외 발생!");
            //throw new RuntimeException("예외 발생!");
        }

        return "member/list";
    }

    @ResponseBody // 반환값을 void하려고 넣음
    @GetMapping({"/info/{id}/{id2}", "/info/{id}"}) // {id} : 경로변수, 바뀔수 있는 경로에 대한 값 -> 변수에 담아야 활용가능 //
    public void info(@PathVariable("id") String email, @PathVariable(name = "id2", required = false) String email2) {
        // "/info/{id}" : 이렇게도 인식하게 하려면 required = false 이거 넣어줘야 오류 안뜨고 잘 뜸
        //@PathVariable("id") String email, @PathVariable("id2") String email2 //string으로 값 안 넣어도 형변환 자동으로 해줌

        log.info("email:{}, email2:{}", email, email2);

    }

    @ResponseBody
    @GetMapping("/list2")
    public List<Member> list() {
        // 10명의 회원추가하고 목록형태로 출력할거임
        List<Member> members = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Member.builder()
                        .email("user" + i + "@test.org")
                        .password("12345678")
                        .userName("사용자" + i)
                        .regDt(LocalDateTime.now())
                        .build())
                .toList();

        return members;
    }

/*
    //@ExceptionHandler({BadRequestException.class, RuntimeException.class})// 발생할 예외를 넣으면 됨
    @ExceptionHandler(Exception.class) // 다형성 // 발생하는 예외 다 여기로 유입되게 함
    public String errorHandler(Exception e, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 멤버 컨트롤러에서 에러가 발생하면 여기로 모두 유입
        // BadRequestException e : 발생한 예외가 여기 담겨지게 됨
        e.printStackTrace();
        log.info("memberController");

        return "error/common";
    }
 */



    /*
    @InitBinder // 특정 컨트롤러에서 사용할 공통적인 Valdiator
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(joinValidator);
    }
     */
}
