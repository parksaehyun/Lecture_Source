package member.services;

import global.exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;

public class LoginService {

    public void process(HttpServletRequest request) {
        // 아이디 : email, 비밀번호 password
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(email == null || email.isBlank()) {
            throw new ValidationException("이메일을 입력하세요.");
        }
    }
}