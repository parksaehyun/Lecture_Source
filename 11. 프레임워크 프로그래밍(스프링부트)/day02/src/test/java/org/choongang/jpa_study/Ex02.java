package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.choongang.member.entities.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex02 {

    @PersistenceContext // 영속성 콘텍스트다 알려주기 위해 @Autowired 말고 이거 사용
    private EntityManager em;

    // 값 10개 임의로 추가
    @BeforeEach
    void init() {
        for (Long i = 1L; i < 10L; i++) {
            Member member = new Member();
            member.setSeq(i);
            member.setEmail("user" + i + "@test.org");
            member.setPassword("123456789");
            member.setUserName("사용자" + i);
            member.setCreatedAt(LocalDateTime.now());
            em.persist(member); // persist() : 영속상태가 됨 = 영속성 메모리에 담아줌
        }

        em.flush(); // flush() : DB 영구 반영 // INSERT 쿼리
        em.clear(); // 영속 상태 엔티티 모두 비우기
    }

    @Test
    void test1() {
        Member member = em.find(Member.class, 1L); // find() :  db에서 꺼내온 데이터를 영속성 콘텍스트안에 담아줌
        System.out.println(member);

        Member member2 = em.find(Member.class, 1L);
        System.out.println(member2);

        System.out.println(member = member2);
        System.out.println("member:" + System.identityHashCode(member));
        System.out.println("member2:" + System.identityHashCode(member2));

        member.setUserName("(수정)사용자1");
        //em.flush(); // UPDATE 쿼리 수행

        // 값 변경 후 삭제 상태 변경 후 해당 데이터를 조회 -> 암묵적으로 flush()
        // 최종상태를 조회해야 하기 때문에
        Member member3 = em.find(Member.class, 1L); // find() : 조회 - db에서 꺼내온 데이터를 영속성 콘텍스트안에 담아줌
        System.out.println(member3);
    }
}
