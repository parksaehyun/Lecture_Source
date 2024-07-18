package org.choongang.global.advices;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.choongang.global.exceptions.CommonException;
import org.choongang.global.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice("org.choongang")
public class RestCommonControllerAdvice {

    @ExceptionHandler(Exception.class) // 예외 받기
    public ResponseEntity<JSONData> errorHandler(Exception e) {
        // <JSONData> : 제이슨 형태로 에러 응답하기
        Object message = e.getMessage();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500으로 고정

        // 우리가 정의한 예외는 다양한 응답코드가 있음
        // instance of 로 출처 쳌 하고 응답코드 가져와서 응답코드 내보내기
        if (e instanceof CommonException commonException) {
            status = commonException.getStatus(); // 응답코드 가져오기

            Map<String, List<String>> errorMessages = commonException.getErrorMessages();
            if (errorMessages != null ) message = errorMessages;
        }

        // 실패시
        JSONData jsonData = new JSONData();
        jsonData.setSuccess(false);
        jsonData.setMessage(message);
        jsonData.setStatus(status);

        e.printStackTrace();

        return ResponseEntity.status(status).body(jsonData);
    }
}
