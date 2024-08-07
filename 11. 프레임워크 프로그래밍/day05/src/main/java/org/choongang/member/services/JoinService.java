package org.choongang.member.services;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.choongang.global.exceptions.BadRequestException;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.controllers.RequestLogin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.choongang.member.validators.JoinValidator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 알아서 final 초기화해줌
public class JoinService {

    //@Autowired
    //private final JoinValidator validator; // 생성자 매개변수로 의존성 객체 생성
    private final MemberMapper mapper;

    public void process(RequestJoin form) {
        // 유효성 검사
        //validator.check(form); // 스프링에서 제공하는 밸리데이터 사용할거라 주석처리

        // 비밀번호 해시화
        String hash = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt(12));

        // DB 저장
        Member member = Member.builder()
                .email(form.getEmail())
                .password(hash)
                .userName(form.getUserName())
                .build();

        int result = mapper.register(member);
        if (result < 1) {
            throw new BadRequestException("회원가입 실패");
        }
    }
}
