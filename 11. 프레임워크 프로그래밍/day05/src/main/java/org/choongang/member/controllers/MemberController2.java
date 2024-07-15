package org.choongang.member.controllers;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/member2")
@RequiredArgsConstructor
public class MemberController2 {

    private final MessageSource messageSource;
    private final HttpServletRequest request; // 브라우저의 언어 정보 가져오기

    @ModelAttribute("commonValue")
    public String commonValue() {
        return "공통 속성값..."; // 컨트롤러 내에서 유지되는 공통 데이터
    }

    @ModelAttribute("hobbies") // form커스텀태그가 체크박스도 지원함
    public List<String> hobbies() {
        return List.of("취미1", "취미2", "취미3", "취미4");
    }

    @ModelAttribute("hobbies2") // form커스텀태그가 체크박스도 지원함
    public List<CodeValue> hobbies2() {
        return List.of(
                new CodeValue("취미1", "hobby1"),
                new CodeValue("취미2", "hobby2"),
                new CodeValue("취미3", "hobby3"),
                new CodeValue("취미4", "hobby4")
        );
    }

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form) {

        Locale locale = request.getLocale(); // 요청 헤더 Accept-Language // 브라우저의 언어설정 // 리퀘스트객체한테서 로케일을 가져올 수 있음
        String message = messageSource.getMessage("EMAIL", null, locale);
        System.out.println(message);

        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(RequestJoin form) {
        //RequestJoin form : 커맨드 객체 - 포스트 방식에서만 넘어옴
        log.info(form.toString());

        return "member/join";
    }

    @GetMapping("/login")
    public String login(RequestLogin2 form) {

        if(form != null) {
            log.info("이메일:{}, 비밀번호: {}", form.email(), form.password());
        }

        return "member/login";
    }

    /*
    @GetMapping("/join")
    public String join(Model model) {
        //속성값을 모델말고 http서블릿리퀘스트로 해도 되지만 뭔가 더 기능이 있어서 모델로...
        // get방식은 커맨드 객체가 없음
        // 직접 주소 입력하는것은 포스트가 아닌 겟방식
        // 값이 없더라도 속성을 설정해줘야 한다 포스트 양식에서의 오류 방지를 위해서
        // 겟방식은 모델을 통해 속성을 정의

        RequestJoin form = new RequestJoin();
        model.addAttribute("requestJoin", form);
        // 비어있는 커맨드 객체임 값이 없음 = 값이 매핑이 안되어 있음
        // 근데 왜 함?
        // 양식에서 오류방지를 위해 비어있는 값임에도 속성설정 해줘야 함
        // 로그인 시 아이디 비번 입력(겟) -> 로그인 실패(포스트) -> 다시 로그인 화면(겟) - 이때 실패했던 로그인 데이터(아이디) 유지

        model.addAttribute("requestJoin", form);

        return "member/join"; // '/' 가 없음 = 상대경로 = 뷰의 경로
    }
     */

    /*
    @PostMapping("/join")
    public String joinPs(RequestJoin form) {

        //return "redirect:/member/login";
        // 찐 페이지 이동 '/'가 있으면 절대경로 = url주소(로케이션헤더추가) // Location: /day05/member/login
        return "forward:/member/login"; // 버퍼 치환(가짜 페이지 이동)
    }
 */


//    private  final Logger log = LoggerFactory.getLogger(MemberController.class); // 로거 연동 = @slf4j
/*
    @GetMapping("/join")
    public String join1() {

        log.info("{}, {} 없음", "mode1", "mode2");

        return "member/join";
    }

    @GetMapping(path="/join", params={"mode=join"})
    public String join() {

        log.info("mode=join");

        return "member/join";
    }



    @PostMapping(path="/join", headers = "appKey=1234", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) //  headers = "appKey=1234" : 요청쪽 헤더에 "appKey=1234" 가 있어야 여기로 유입이 될 수 있음
    public String joinPs(RequestJoin form) {

        log.info("joinPs 실행...");

        return "redirect:/member/login";
    }
 */

    /*
    @GetMapping("/member/join")
    public ModelAndView join() {

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "안녕하세요.");
        mv.setViewName("member/join");

        return mv;
    }*/
}
