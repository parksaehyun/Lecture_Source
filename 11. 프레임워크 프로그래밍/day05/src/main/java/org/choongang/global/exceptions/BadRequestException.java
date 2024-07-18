package org.choongang.global.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class BadRequestException extends CommonException{
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST); // 응답코드 400내보내기
    }

    public BadRequestException(Map<String, List<String>> errorMessages) {
        super(null, HttpStatus.BAD_REQUEST);
        setErrorMessages(errorMessages); // 변환된 메세지를 넣음
    }
}
