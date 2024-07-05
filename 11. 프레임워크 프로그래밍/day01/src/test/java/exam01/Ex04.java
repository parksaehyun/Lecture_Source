package exam01;

import exam01.config.AppCtx3;
import exam01.member.controllers.RequestJoin;
import exam01.member.services.InfoService;
import exam01.member.services.JoinService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex04 {

    @Test
    void test1() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx3.class);

        JoinService joinService = ctx.getBean(JoinService.class); // 컨테이너안에 있는 객체 꺼내오기 의존성도 다 주입되어 있음
        InfoService infoService = ctx.getBean(InfoService.class);

        // 데이터 클래스는 매번 객체 생성해야함 = 관리하면 안됨
        // 보통 컨테이너가 관리하는 객체는 기능적인 클래스들
        RequestJoin form = RequestJoin.builder()
                .email("user01@test.org")
                .password("12345678")
                .confirmPassword("12345678")
                .userName("사용자01")
                .build();
        joinService.process(form);

        infoService.printList();

        ctx.close();
    }
}
