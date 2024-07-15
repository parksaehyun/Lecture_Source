package org.choongang.member.controllers;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RequestJoin {
    @NotBlank(message = "이메일을 입력하세요.") // @NotBlank : 필수항목 검증 // (message = "이메일을 입력하세요.") : 에러발생 시 출력할 문구 정의
    @Email(message = "이메일 형식이 아닙니다.") // 이메일 형식체크
    private String email;

    @NotBlank
    @Size(min=8) // 비밀번호자리수(최소 8자리이상)
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String userName;

    @AssertTrue
    private boolean agree;

    /*
    private String email;
    private String password;
    private String confirmPassword;
    private String userName;
    //private String[] hobby;
    //private Set<String> hobby;
    //private List<String> hobby;
    private String hobby;
    private boolean agree;

    private Address addr; // addr이 속성명 // 중첩된 커맨드 객체
     */
}
