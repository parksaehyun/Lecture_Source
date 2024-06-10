package member.validators;

import global.exceptions.ValidationException;
import global.validators.RequiredValidator;
import global.validators.Validator;
import jakarta.servlet.http.HttpServletRequest;

public class LoginValidator implements Validator<HttpServletRequest>, RequiredValidator {
    @Override
    public void check(HttpServletRequest form) {
        String email = form.getParameter("email");
        String password = form.getParameter("password");

        // 필수항목 검증 시작
        checkRequired(email, new ValidationException("아이디를 입력하세요."));
        checkRequired(password, new ValidationException("비밀번호를 입력하세요."));
        // 필수항목 검증 끝
    }
}
