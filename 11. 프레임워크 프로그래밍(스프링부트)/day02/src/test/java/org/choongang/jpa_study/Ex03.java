package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.choongang.member.entities.Member;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest // 의존성주입 설정 확장기능 다 포함됨
@Transactional // 기본값 : 롤백
public class Ex03 {

    @PersistenceContext
    private EntityManager em;

    @Test
    void test1() {
        Member member = new Member();
        member.setEmail("user01@test.org");
        member.setPassword("123456789");
        member.setUserName("사용자01");
        member.setCreatedAt(LocalDateTime.now());

        em.persist(member); // 영속성에 추가

        Member member2 = new Member();
        member2.setEmail("user01@test.org");
        member2.setPassword("123456789");
        member2.setUserName("사용자01");
        member2.setCreatedAt(LocalDateTime.now());

        em.persist(member2); // 영속성에 추가

        em.flush(); // db에 저장 // INSERT 2번

        em.clear(); // 영속 상태 엔티티 모두 비우기

        Member _member1 = em.find(Member.class, member.getSeq());
        System.out.println(_member1); // 영속성 없으니 db조회할거임 = SELECT

        Member _member2 = em.find(Member.class, member2.getSeq());
        System.out.println(_member2); // 영속성에 있는 데이터 조회

        // 증감번호 확인하려면 기존데이터가 남아있어야 함
        // application.yml 에서 ddlAuto: create -> update 로 변경
    }
}
