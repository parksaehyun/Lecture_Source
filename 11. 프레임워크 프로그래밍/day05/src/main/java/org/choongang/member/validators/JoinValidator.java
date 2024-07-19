package org.choongang.member.validators;


import lombok.RequiredArgsConstructor;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.mappers.MemberMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {
//public class JoinValidator implements Validator<RequestJoin>, RequiredValidator {

    private final MemberMapper mapper;

    @Override
    public boolean supports(Class<?> clazz) { // 검증하고자 하는 커맨드 객체를 한정할 때 사용 // 리퀘스트 조인 커맨드 객체만 검증하도록 제한
        return clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) { // Object target : 커맨드 객체
        /**
         * 1. 필수 항목 검증 (email, password, confirmPassword, userName, agree)
         * 2. 이메일 중복 여부(회원이 가입되어 있는지 체크)
         * 3. 비밀번호 자리수 체크(8자리)
         * 4. 비밀번호, 비밀번호 확인 일치 여부
         **/

        RequestJoin form = (RequestJoin) target;
        // 필수항목 검증 위해 필요한 데이터 가져오기 // 특정 필드에 한정해서 검증
        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String userName = form.getUserName();
        boolean agree = form.isAgree();

        /*
        // 필수항목 검증
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required", "이메일을 입력하세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required", "비밀번호를 입력하세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "Required", "비밀번호를 확인하세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "Required", "회원명을 입력하세요.");

        if(!agree) {
            errors.rejectValue("agree", "Required", "회원 가입 약관에 동의하세요.");
        }
         */

        // 2. 이메일 중복 여부(회원이 가입되어 있는지 체크) // 이메일 필드에 한정한 에러 = 리젝트 밸루 = 필드에러 = 커맨드 객체 에러
        if (StringUtils.hasText(email) && mapper.exists(email) != 0L) {
            errors.rejectValue("email", "Duplicated");
        }
/*
        // 3. 비밀번호 자리수 체크(8자리)
        if (StringUtils.hasText(password) && password.length() < 8) {
            errors.rejectValue("password", "Length");
        }
 */

        // 4. 비밀번호, 비밀번호 확인 일치 여부
        if (StringUtils.hasText(password) && StringUtils.hasText(confirmPassword) && !password.equals(confirmPassword)) {
            errors.rejectValue("confirmPassword", "Mismatch");
        }

        //if (email == null || email.isBlank()) {} // StringUtils사용하자

        /* 이거 너무 길다 ValidationUtils 사용하자
        if (!StringUtils.hasText(email)) {
            errors.rejectValue("email", "Required", "이메일을 입력하세요."); // ("필드명", "에러코드", "기본메세지") // 아직 에러코드는 등록안함
        }

        if (!StringUtils.hasText(password)) {
            errors.rejectValue("password", "Required", "비밀번호를 입력하세요."); // ("필드명", "에러코드", "기본메세지")
        }
         */


    }
/*
    @Override
    public void check(RequestJoin form) {
        /**
         * 1. 필수 항목 검증 (email, password, confirmPassword, userName, agree)
         * 2. 이메일 중복 여부(회원이 가입되어 있는지 체크)
         * 3. 비밀번호 자리수 체크(8자리)
         * 4. 비밀번호, 비밀번호 확인 일치 여부


        // 1. 필수 항목 검증
        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String userName = form.getUserName();
        boolean result = form.isAgree();
*/
        /*
        checkRequired(email, new BadRequestException("이메일을 입력하세요."));
        checkRequired(password, new BadRequestException("비밀번호를 입력하세요."));
        checkRequired(confirmPassword, new BadRequestException("비밀번호를 확인하세요."));
        checkRequired(userName, new BadRequestException("회원명을 입력하세요."));

        checkTrue(result, new BadRequestException("약관에 동의하세요."));

        // 2. 이메일 중복 여부(회원이 가입되어 있는지 체크)
        checkTrue(mapper.exists(email) > 0L, new BadRequestException("이미 가입된 이메일 입니다."));

        // 3. 비밀번호 자리수 체크
        checkTrue(password.length() >= 8, new BadRequestException("비밀번호는 8자리 이상 입력하세요."));

        // 4. 비밀번호, 비밀번호 확인 일치 여부
        checkTrue(password.equals(confirmPassword), new BadRequestException("비밀번호가 일치하지 않습니다."));
    */
}
