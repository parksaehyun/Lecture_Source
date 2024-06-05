package tests;

import jakarta.servlet.http.HttpServletRequest;
import member.services.LoginService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private LoginService loginService; // 매번 테스트 할 때 마다 객체 생성해야 하는 번거로움 해결
    private HttpServletRequest request;

    @BeforeEach
    void init() {
        loginService = new LoginService();
    }

    @Test
    @DisplayName("로그인 성공시 예외가 발생하지 않음")
    void successTest() {
        assertDoesNotThrow(() -> { // 예외가 발생하지 않으면 통과
            loginService.process(request);
        });
    }
}
