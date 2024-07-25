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
@ActiveProfiles("test")
@Transactional // 원 데이터의 엔티티가 계속 영속상태로 유지되도록 해 줌 // 영속상태가 유지안되면 원데이터를 가져올 수 없음 // jpa에선 트랜잭션이 영속성유지하는 형태로 동작이 바뀌어 있음
public class Ex09 {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardDataRepository boardDataRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach // 회원1명 게시글 10개 추가할거임
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
    void test1() {
        BoardData item = boardDataRepository.findById(1L).orElse(null);

        Member member = item.getMember(); // 게시글을 작성한 회원
        System.out.println(member);

        //System.out.println(item);
    }

    @Test // 회원을 먼저 조회하고 그 회원을 바탕으로 게시글 조회하거임
    void test2() {
        Member member = memberRepository.findById(1L).orElse(null);
        List<BoardData> items = member.getItems();
        items.forEach(System.out::println);
    }
}
