package org.choongang.global.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CommonException{
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST); // 응답코드 400내보내기
    }
}
