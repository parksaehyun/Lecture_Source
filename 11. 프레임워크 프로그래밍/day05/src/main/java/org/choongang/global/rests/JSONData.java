package org.choongang.global.rests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor // 데이터 기본생성자도 필요
@RequiredArgsConstructor
public class JSONData {
    // 응답이 제이슨형태로 온거 데이터 형식 고정해주기
    // 매 요청시 마다 형식이 다르면 안되기 때문에 형식 고정
    // 통일된 형식으로 응답하기 위해서 틀을 정한거다
    // 실패 -> 메세지
    // 성공 -> 데이터전송(형식이 다양하게 옴 -> 자료형 Object) + 메세지

    private HttpStatus status = HttpStatus.OK; // 응답코드 // 응답코드도 200이 많으니 고정
    private boolean success = true;  // 보통은 성공값이 많을 거 그래서 true로 고정
    private Object message; // 자료형 Object : 문자도 가능, map도 가능 커맨드 객체 검증(map형태)도 하기에
    @NonNull
    private Object data;
    // @NonNull : 생성자 매개변수로 바로 내보낼 수 있게 할거
    // 자료형 Object
}
