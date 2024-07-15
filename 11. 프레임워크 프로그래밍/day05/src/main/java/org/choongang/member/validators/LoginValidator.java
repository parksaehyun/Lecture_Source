package org.choongang.member.validators;

import lombok.RequiredArgsConstructor;
import org.choongang.member.controllers.RequestLogin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component // 자동 스캔
@RequiredArgsConstructor
public class LoginValidator implements Validator { // 무조건 오버라이딩 해야하는 추상메서드 : supports, validate

    /*
    @Autowired // 의존성 주입
    private MemberMapper mapper;
     */

    private final MemberMapper mapper; // 의존성 주입 : db조회용


    @Override
    //검증하고자 하는 커맨드 객체를 한정할 때 사용 // 리퀘스트 로그인 커맨드 객체만 검증하도록 제한
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestLogin.class);
    }

    @Override
    public void validate(Object target, Errors errors) { // Object target : 커맨드 객체

        // Bean Validation 검증 실패시에는 다음 검증을 진행 X
        if (errors.hasErrors()) {
            return;
        }

        /**
         * 1. 필수 항목 검증 (email, password) -> Bean Validation
         * 2. 이메일로 회원이 조회되는지 검증
         * 3. 조회된 회원의 비밀번호가 입력한 값과 일치하는지 검증
         **/

        RequestLogin form = (RequestLogin) target;
        // 필수항목 검증 위해 필요한 데이터 가져오기 // 특정 필드에 한정해서 검증
        String email = form.getEmail();
        String password = form.getPassword(); // 사용자가 입력한 비번

        /*
        // 2. 이메일로 회원이 조회되는지 검증 - 내가 쓴거
        if(StringUtils.hasText(email)  && mapper.exists(email) != 0L) {
            errors.rejectValue("email", "EmptyEmail");
        }
         */

        // 강사님꺼
        if (StringUtils.hasText(email)) {
            Member member = mapper.get(email);
            if (member == null) {
                //errors.rejectValue("email", "Check.emailPassword"); // 특정필드에 한정하여 에러 -> 그 필드에서 에러가 나왔구나 예측이 가능하다(보안성 취약)
                errors.reject("Check.emailPassword"); // 특정필드에 한정하지 않고 커맨드객체 그 자체의 에러
            }

        /*
        // 3. 조회된 회원의 비밀번호가 입력한 값과 일치하는지 검증 - 내가쓴거
        if (StringUtils.hasText(password) && !password.equals(form.getPassword())){
            errors.rejectValue("password", "MisMatch");
        }
         */

        // 강사님꺼
        if (member != null && StringUtils.hasText(password) && !BCrypt.checkpw(password, member.getPassword())) {
            //errors.rejectValue("password", "Check.emailPassword"); // 특정필드에 한정하여 에러 -> 그 필드에서 에러가 나왔구나 예측이 가능하다(보안성 취약)
            errors.reject("Check.emailPassword"); // 특정필드에 한정하지 않고 커맨드객체 그 자체의 에러
        }

        }
    }
}
