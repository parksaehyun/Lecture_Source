package org.choongang.member.mappers;

import org.choongang.config.MvcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig // 목mvc 사용가능하게끔? 해줌
@ContextConfiguration(classes = MvcConfig.class) // 설정 클래스
public class MapperTest {
    // 의존성 주입 되는지 쳌
    @Autowired
    private MemberMapper mapper;

    @Test
    void test1() {
        long total = mapper.getTotal();
        System.out.println(total);
    }

}
