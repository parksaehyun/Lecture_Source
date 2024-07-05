package exam01.member.services;

import exam01.member.controllers.RequestJoin;
import exam01.member.dao.MemberDao;
import exam01.member.entities.Member;
import exam01.member.validators.JoinValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JoinService {

    //@Autowired // 컨테이너 안에서 찾아보고 객체를 주입해줌
    private JoinValidator validator;
    //@Autowired
    @NonNull
    private MemberDao memberDao;


    // 의존관계 - 없으면 객체 생성x
    // 의존성을 주입하는 이유 : 통제하기 위해서
    public JoinService(JoinValidator validator, MemberDao memberDao) {
        this.validator = validator;
        this.memberDao = memberDao;
    }


    /*
    // 연관 관계 - 없어도 객체는 생성 된다.
    public void setValidator(JoinValidator validator) {
        this.validator = validator;
    }
     */

    public void process(RequestJoin form) {
        // 서비스 쪽으로 데이터가 넘어옴
        // 이것도 의존임
        validator.check(form);
        // joinService는 validator객체 form객체를 의존 -> 의존성

        // 데이터 영구 저장 - DAO(Data Access Object) // 마이바티스 매퍼
        Member member = Member.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .userName(form.getUserName())
                .regDt(LocalDateTime.now())
                .build();

        memberDao.register(member);
    }
}
