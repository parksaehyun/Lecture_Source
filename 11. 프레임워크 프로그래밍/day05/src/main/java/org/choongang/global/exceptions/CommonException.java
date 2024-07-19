package org.choongang.global.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class CommonException extends RuntimeException{
    private HttpStatus status; // 스프링이 지원해 주는 이넘상수 응답코드 // 응답코드 설정

    private Map<String, List<String>> errorMessages; // 커맨드 객체 검증 시 에러 여기에 담음 -> 모든에러의 출력을 통일 성있게 하기 위해서(다형성)

    /*
    private void setErrorMessages(Map<String, List<String>> errorMessages) {
        this.errorMessages = errorMessages;
    }
     */

    public CommonException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR); // 응답코드가 없이 그냥 호출하면 500 내보내주기용
    } // message : 게시글이 없다 등등

    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    /* 롬복쓸거임
    public HttpStatus getStatus() {
        return status; // 응답코드 넣음??
    }
     */
}
