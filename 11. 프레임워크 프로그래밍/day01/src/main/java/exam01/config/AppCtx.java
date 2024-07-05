package exam01.config;

import exam01.Greeter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// 설정 클래스 - 스프링 컨테이너가 관리할 객체를 정의, 설정
//@Import(value={AppCtx2.class})// 설정 클래스 추가
//@Import({AppCtx2.class}) // 설정이 한개이면 value 생략가능
@Import(AppCtx2.class) // 설정이 한개이면 {} 생략가능
@Configuration
public class AppCtx {

    @Bean // 스프링이 관리할 객체라는 것을 알려주는 것 - 스프링이 이걸보고 객체관리를 해 줌
    public Greeter greeter() {
        return new Greeter();
    }
}
