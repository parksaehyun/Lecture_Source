package exam01;

import config.AppCtx;
import member.controllers.RequestJoin;
import member.services.InfoService;
import member.services.JoinService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
    @Test
    void test1() {
        // 스프링 컨테이너 객체생성
        // AnnotationConfig - 설정 방식
        // ApplicationContext
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class); // 스캔해주는 역할

        JoinService joins = ctx.getBean(JoinService.class); // 스캔 + 스프링컨테이너 관리객체여야 함

        ctx.close();
    }

    @Test
    void test2() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        JoinService joinService = ctx.getBean(JoinService.class);
        InfoService infoService = ctx.getBean(InfoService.class);

        RequestJoin form = RequestJoin.builder()
                .email("user01@test.org")
                .password("123456789")
                .confirmPassword("123456789")
                .userName("나다")
                .build();

        joinService.process(form);
        infoService.printList();

        ctx.close();
    }
}
