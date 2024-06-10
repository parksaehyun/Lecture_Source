package tests;

import com.github.javafaker.Faker;
import global.exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import member.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@DisplayName("로그인 서비스 기능 테스트")
public class LoginServiceTest {

    private LoginService loginService;
    private HttpServletRequest request;
    private Faker faker;

    @BeforeEach
    void init() {
        loginService = new LoginService();

        // HttpServletRequest 모의 객체 생성
        request = mock(HttpServletRequest.class);
        faker = new Faker(Locale.KOREAN);

        //given(request.getParameter("email")).willReturn("test01@test.org");
        //given(request.getParameter("password")).willReturn("12345678");
        //given(request.getParameter("123")).willThrow(IllegalAccessError.class);
    }

    void setParamData(String name, String value) {
        given(request.getParameter(name)).willReturn(value);
    }

    void setSuccessData() {
        String password = faker.regexify("[\\w]{8}").toLowerCase();
        setParamData("email", faker.internet().emailAddress());
        setParamData("password", password);
    }

    @Test
    @DisplayName("로그인 성공시 예외 발생 없음")
    void successTest() {
        assertDoesNotThrow(() -> {
            setSuccessData();
            loginService.process(request);
        });
    }

    @Test
    @DisplayName("필수 항목(아이디, 비밀번호) 검증, 검증 실패시 ValidationException 발생")
    void requiredFieldTest() {
        // 아이디 필수 항목 검증
        assertThrows(ValidationException.class, () -> {
            loginService.process(request);
        });
    }
}