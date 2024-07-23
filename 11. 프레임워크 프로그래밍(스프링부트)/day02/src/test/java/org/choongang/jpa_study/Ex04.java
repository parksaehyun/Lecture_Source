package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.choongang.member.entities.Member;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest // 의존성주입 설정 확장기능 다 포함됨
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex04 {

    @PersistenceContext
    private EntityManager em;

    @Test
    void test1() throws Exception {
        Member member = new Member();
        // seq는 자동 증감해서 따로 안넣어줄거임
        // 날짜도 알아서 들어갈거라서 안넣어줌
        member.setEmail("user01@test.org");
        member.setPassword("123456789");
        member.setUserName("사용자01");

        em.persist(member); // 영속성에 추가
        em.flush(); // db에 저장

        em.clear(); // 영속 상태 엔티티 모두 비우기
        // 이미 영속성에 있으면 db에서 조회 안하고 바로 영속성데이터 가져오기 때문에 db에서 데이터 가져오기 위해 clear()함

        member = em.find(Member.class, member.getSeq());
        System.out.println(member);

        Thread.sleep(5000); // 5초 뒤
        // @UpdateTimestamp 값 확인위해 이렇게 함 // 추가한시간과 수정한 시간의 차이 = 5초
        member.setUserName("(수정)사용자01");
        em.flush();
        em.clear();

        member = em.find(Member.class, member.getSeq());
    }
}
