package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.choongang.member.entities.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex01 {

    //@Autowired // 이미 스프링 데이터 쪽에 빈으로 등록되어 있음
    //private EntityManagerFactory emf; // 엔티티 매니저 생성

    @PersistenceContext
    private EntityManager em;

    @Test
    void test() {
        //EntityManager em = emf.createEntityManager(); // 이미 스프링 데이터 쪽에 빈으로 등록되어 있음
        // EntityManager 생성
        // EntityManager : 영속성메모리에 넣어줄거 = 상태변화에 따라 쿼리 자동으로 실행되게금 해주는 역할

        //EntityTransaction tx = em.getTransaction(); // 트랜젝션 수동관리

        //tx.begin(); // 트랙잭션 시작

        Member member = new Member();
        member.setSeq(1L);
        member.setEmail("user01@test.org");
        member.setPassword("123456789");
        member.setUserName("사용자01");
        member.setCreatedAt(LocalDateTime.now());

        em.persist(member); // persist() : 영속성(변화감지 상태) 메모리에 넣어주는 메서드 // 영속성 컨텍스트에 엔티티를 영속 : 상태 감지 시작
        // 영속상태 - 변화 감지 메모리에 있다 = 변화감지...

        em.flush(); //  DB 반영 // INSERT 쿼리 // 처음 추가된 엔티티라 INSERT

        em.detach(member); // 영속상태 분리 - 변화 감지x

        member.setUserName("(수정)사용자01"); // 변경
        member.setModifiedAt(LocalDateTime.now());
        em.flush(); // UPDATE 쿼리 // 영속성안에 있는 값이 바뀌었다 -> UPDATE
        // 메모리 기반 db에 있이 때문에 실제 db에 있진 않음

        em.merge(member);
        em.flush();

        //em.remove(member); // 제거상태, 제거 x, 상태만 제거상태
        //em.flush(); // DELETE 쿼리 // 실제로 flush을 해야 찐 제거됨

        //tx.commit(); // 트랜잭션 커밋
    }
}
