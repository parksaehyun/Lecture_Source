package member.services;

import global.exceptions.ValidationException;
import member.controllers.RequestJoin;

public class JoinService {

    public void process(RequestJoin form) {
        String email = form.getEmail();
        if( email == null || email.isBlank()) {
            throw new ValidationException("이메일을 입력하세요.");
        }

        String password = form.getPassword();
        if (password == null || password.isBlank()) {
            throw new ValidationException("비밀번호를 입력하세요.");
        }
    }
}