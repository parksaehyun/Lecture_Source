package org.choongang.member.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.member.controllers.RequestLogin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    // 의존성 추가
    private final MemberMapper mapper;
    private final HttpSession session; // 세션에 회원 정보를 유지
    private final HttpServletResponse response;
    // 쿠키는 서버가 필요함
    // = 서버는 브라우저에게 부탁함 = 브라우저에게 요청 = 응답헤더통해서만 가능

    // 세션은 내 브라우저에서만 내 정보만 유지 되어야 함
    // 쿠키에 바로 저장은 보안이 불안하니 서버쪽에서... 세션아이디를 통해 브라우저 구분
    // 쿠키값 조회는 요청헤더 통해서 조회

    public void process(String email) { // 비번은 검증이 끝났으니 이메일 가지고만 처리하자
        /**
         * 1. email로 회원 조회
         * 2. 세션에 회원 정보를 유지
         */


        // email로 회원 조회
        Member member = mapper.get(email);
        if (email == null) {
            return;
        }

        // 세션에 회원 정보를 유지
        session.setAttribute("member", member);
    }
    public void process(RequestLogin form) {
        process(form.getEmail());  // 실제 처리는 process(String email)

        // 쿠키는 resp(응답)통해서 받아옴 쿠키는 서버가 필요하지만 브라우저에 저장되어 있음 응답헤더 통해서 셋쿠키 해서 저장?

        /* 이메일 기억하기 처리 */
        Cookie cookie = new Cookie("savedEmail", form.getEmail());
        if (form.isSaveEmail()) { // 쿠키 등록
            cookie.setMaxAge(60 * 60 * 24 * 7); // 7일간 쿠키 유지

        } else { // 쿠키 제거
            cookie.setMaxAge(0);
        }

        response.addCookie(cookie);
    }
}
