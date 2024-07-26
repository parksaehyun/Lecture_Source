package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.choongang.board.entities.BoardData;
import org.choongang.board.repositories.BoardDataRepository;
import org.choongang.member.constants.Authority;
import org.choongang.member.entities.Member;
import org.choongang.member.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class Ex13 {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardDataRepository boardDataRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
        // 회원1명 게시글 10개 추가할거임
    void init() {
        Member member = Member.builder()
                .email("user01@test.org")
                .password("123456789")
                .userName("사용자01")
                .authority(Authority.USER)
                .build();

        memberRepository.saveAndFlush(member);

        List<BoardData> items = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> BoardData.builder()
                        .subject("제목" + i)
                        .content("내용" + i)
                        .member(member) // 외래키 값
                        .build()).toList();

        boardDataRepository.saveAllAndFlush(items);
        em.clear(); // 1차캐시 때문에 영속성에 있으면 쿼리안하니 쿼리보기 위해 영속성에서 비우기
    }
    @Test
    void test2() { // orphanRemoval=true
        Member member = memberRepository.findById(1L).orElse(null); //회원 1명 가져오기

        List<BoardData> items = member.getItems(); // member - 부모 / 보드데이터 - 자식
        items.remove(0); // 메모리만 제거 / 엔티티를 제거한게 아님(영속성 콘텍스트 안에 있는게 제거된게 아님)
        // 엔티티는 전부 영속성 콘텍스트 안에 있음
        items.remove(1);
        // 0번과 1번을 고아로 만듬

        memberRepository.flush();
    }

    @Test
    void test1() { // CASCADE : REMOVE
        Member member = memberRepository.findById(1L).orElse(null); //회원 1명 가져오기

        memberRepository.delete(member); // 회원(부모) + 게시글 데이터(자식) 삭제

        memberRepository.flush(); // db반영
    }
}
