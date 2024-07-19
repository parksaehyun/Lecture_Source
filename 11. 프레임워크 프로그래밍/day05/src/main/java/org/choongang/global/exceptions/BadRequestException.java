package org.choongang.global.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class BadRequestException extends CommonException{
    // 일반 메세지 형태 에러
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST); // 응답코드 400내보내기
    }
// 커맨드 객체 에러 // 제이슨형태
    public BadRequestException(Map<String, List<String>> errorMessages) {
        super(null, HttpStatus.BAD_REQUEST);
        setErrorMessages(errorMessages); // 변환된 메세지를 넣음 // 커먼 익셉션꺼 // 모든 에러 처리를 공통으로 하기 위해 커먼에서 처리한거 가져오기
        // errorMessages = 가공된 필드에러 메세지
    }
}
