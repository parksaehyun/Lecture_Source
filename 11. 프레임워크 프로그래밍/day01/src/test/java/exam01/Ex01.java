package exam01;

import exam01.config.AppCtx;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
    @Test
    void test1() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class); // 스프링 컨테이너 객체

        // 어떤 객체를 꺼내올지 알려주기
        // 메서드명이 객체의 이름 = 메서드명이 빈의 이름 = greeter
        Greeter g1 = ctx.getBean("greeter", Greeter.class); // 메서드명으로 컨테이너에서 객체 꺼내오기
        g1.hello("이이름");

        Greeter g2 = ctx.getBean("greeter", Greeter.class);
        g2.hello("김이름");

        System.out.println(g1 == g2); // 주소 비교 -> true = 싱글톤 형태로 객체관리를 해준다

        ctx.close();
    }

    @Test
    void test2() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        // 싱글톤으로 기본 관리하므로 객체가 한개만 있는 경우가 많다
        // class 클래스만 있어도 찾는다.
        Greeter g1 = ctx.getBean(Greeter.class);
        g1.hello("이이름");

        ctx.close();
    }
}
