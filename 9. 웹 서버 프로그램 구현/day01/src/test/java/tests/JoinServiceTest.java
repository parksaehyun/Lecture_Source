package tests;

import global.exceptions.ValidationException;
import member.controllers.RequestJoin;
import member.services.JoinService;
import member.validators.JoinValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("회원가입기능 테스트")
public class JoinServiceTest {

    private JoinService joinService;

    @BeforeEach
    void init() {
        joinService = new JoinService(new JoinValidator());
    }

    RequestJoin getData() {
        return RequestJoin.builder()
                .email("test" + System.currentTimeMillis() + "@test.org")
                .password("12345678")
                .confirmPassword("12345678")
                .userName("❤귤귤❤")
                .termsAgree(true)
                .build();
    }

    @Test
    @DisplayName("회원가입 성공 시 예외가 발생 없음")
    void successTest() {
        assertDoesNotThrow(() -> {
            joinService.process(getData());
        });
    }

    @Test
    @DisplayName("필수항목(이메일, 비밀번호, 비밀번호 확인, 회원명, 약관동의) 검증, 검증 실패 시 ValidationException 발생")
    void requiredFieldTest() {
        assertAll(
                // 이메일 검증
                () -> {
                    RequestJoin form = getData();
                    //form.setEmail(null);
                    requiredFieldEackTest(form, "이메일");

                    form.setEmail("   ");
                    requiredFieldEackTest(form, "이메일");
                    },

                // 비밀번호 검증
                    () -> {
                        RequestJoin form = getData();
                        form. setPassword(null);
                        requiredFieldEackTest(form, "비밀번호");

                        form. setPassword("    ");
                        requiredFieldEackTest(form, "비밀번호");
                    },
                // 비밀번호 확인 검증
                    () -> {
                        RequestJoin form = getData();
                        form.setConfirmPassword(null);
                        requiredFieldEackTest(form, "비밀번호를 확인");

                        form.setConfirmPassword("    ");
                        requiredFieldEackTest(form, "비밀번호를 확인");
                    },
                // 회원명 검증
                    () -> {
                        RequestJoin form = getData();
                        form.setUserName(null);
                        requiredFieldEackTest(form, "회원명");

                        form.setUserName("   ");
                        requiredFieldEackTest(form, "회원명");
                    },
                // 약관동의
                    () -> {
                        RequestJoin form = getData();
                        form.setTermsAgree(false);
                        requiredFieldEackTest(form, "약관에 동의");
                    }
        );
    }

    void requiredFieldEackTest(RequestJoin form, String keyword) {
        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            joinService.process(form);
        }, keyword + "오류!");

        String message = thrown.getMessage();
        assertTrue(message.contains(keyword));
    }

    @Test
    @DisplayName("비밀번호와 비밀번호 확인 일치 테스트, 검증 실패시 ValidationException 발생")
    void passwordMatchTest() {

    }

    @Test
    @DisplayName("이메일 중복 여부 테스트, 검증 실패 시 DuplicatedMemberException 발생")
    void duplicateEmailTest() {

    }

    @Test
    @DisplayName("회원가입 완료 후 실제 데이터베이스에서 조회 되는지 검증")
    void joinmemberExistTest() {
        // 가입처리

        // getData() 로 생성한 데이터의 email 항목으로 db 조회

        // 조회한 회원 데이터 email과 getData()로 생성한 email이 일치하는지 체크
        // assertEquals(...)

    }
}

