package org.choongang.global;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

@Component // rest에러 메세지 내가 설정한거 보내주기
@RequiredArgsConstructor
public class Utils {
    // 필드명, 메세지명 // map형태 - 키 :필드명  값 : 메세지명

    private final MessageSource messageSource;
    private final HttpServletRequest request; // 요청쪽의 언어를 알기 위해 가져옴 // 브라우저의 로케일 설정 가져오기

    public Map<String, List<String>> getErrorMessage(Errors errors) {
        // FieldErrors처리
        Map<String, List<String>> messages = errors.getFieldErrors()
                .stream()
                .collect(Collectors.toMap (FieldError::getField, e -> getCodeMessages(e.getCodes())));
                // 콜렉트로 모아서 map으로 바꾸기
                // f.getField() : 키
                // e -> getCodeMessages(e.getCodes()))) :메세지명 =  에러코드 -> 문자열 데이터로 바꿔줄거임 -> 그럴러면 가공을 해야함

        // GlobalErrors처리
        List<String> gMessages = errors.getGlobalErrors()
                .stream()
                .flatMap(e -> getCodeMessages(e.getCodes()).stream()).toList();

        if (!gMessages.isEmpty()) {
            messages.put("global", gMessages);
        }

        return messages;
    }

    // 에러코드 문자열 데이터로 바꿔줄거임 -> 가공을 해야함
    // 여기서 가공
    public List<String> getCodeMessages(String[] codes) {
        // List : 특정 필드의 에러메세지가 여러개 인 상황이 있어서
        ResourceBundleMessageSource ms = (ResourceBundleMessageSource) messageSource;
        ms.setUseCodeAsDefaultMessage(false); // 메세지가 없으면 코드 그자체로 메세지 출력 -> 예외발생 방지하려고 이렇게 한거임 // 싱글톤임
        // 등록되지 않은 메세지는 필요 없으니 폴스함

        List<String> messages = Arrays.stream(codes)
                .map(c -> {
                    try {
                        return ms.getMessage(c, null, request.getLocale()); // 요청쪽의 언어를 알기 위해 가져옴 // 브라우저의 로케일 설정 가져오기
                    } catch (Exception e) {
                        return ""; // 등록되지 않은 메세지는 필요 없으니 폴스함 -> 근데 이러면 널포인트익셉션이 발생하니 없을때는 빈값 넣어줌
                    }
                })
                .filter(s -> s != null && !s.isBlank())
                .toList(); // 리스트 형태로 반환

        ms.setUseCodeAsDefaultMessage(true); // 싱글톤이라 쓰고나서 다시 트루로 바꿈 // 안그럼 계속 폴스 상태

        return messages;
    }
}
