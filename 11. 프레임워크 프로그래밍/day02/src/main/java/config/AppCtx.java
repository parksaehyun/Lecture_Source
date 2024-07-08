package config;

import board.services.BoardService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
//@ComponentScan(value="member") // member라는 디렉토리리를 스캔범위로 잡음
/* 애노테이션 배재
@ComponentScan(basePackages="member",
    excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = ManualBean.class)
 */

/* 클래스 배재
 @ComponentScan(basePackages = "member",
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MemberDao.class, JoinValidator.class})
 */
/* 정규표현식 배재
@ComponentScan(basePackages = "member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "member\\.*\\w*Dao") // 끝나는게 Dao로 킅나는 클래스
) // basePackages = value = 스캔범위 설정
 */
/*
@ComponentScan(basePackages = {"member", "board"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "member..*Dao") // 끝나는게 Dao로 킅나는 클래스
) // basePackages = value = 스캔범위 설정 // board를 스캔범위 추가 = BoarService 객체가 될 것A
*/

@ComponentScan(basePackages = {"member", "board"})
public class AppCtx {
    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BoardService2 boardService() {
        return new BoardService2();
    }
}
