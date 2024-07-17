package org.choongang.global.exceptions;

import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException{
    private HttpStatus status; // 스프링이 지원해 주는 이넘상수 응답코드 // 응답코드 설정

    public CommonException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR); // 응답코드가 없이 그냥 호출하면 500 내보내주기용
    } // message : 게시글이 없다 등등

    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status; // 응답코드 넣음??
    }
}
