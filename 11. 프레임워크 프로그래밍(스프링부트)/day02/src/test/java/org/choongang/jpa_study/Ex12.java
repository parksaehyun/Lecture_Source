package org.choongang.jpa_study;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.choongang.board.entities.BoardData;
import org.choongang.board.entities.QBoardData;
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
@Transactional // 엔티티 영속성 유지
public class Ex12 {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardDataRepository boardDataRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

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
    void test7() {
        QBoardData boardData = QBoardData.boardData;

        BooleanBuilder andBuilder = new BooleanBuilder();
        andBuilder.and(boardData.subject.contains("제목"))
                .and(boardData.member.email.eq("user01@test.org"));
        /*
        BooleanBuilder orBuilder = new BooleanBuilder();
        orBuilder.or(boardData.seq.eq(2L))
                .or(boardData.seq.eq(3L))
                .or(boardData.seq.eq(4L));

        andBuilder.and(orBuilder);
        */

        PathBuilder<BoardData> pathBuilder = new PathBuilder<>(BoardData.class, "boardData");


        JPAQuery<BoardData> query = jpaQueryFactory.selectFrom(boardData)
                .leftJoin(boardData.member)
                .fetchJoin()
                .where(andBuilder)
                .offset(3) // 조회 시작 레코드 위치 3번 행부터 조회 시작
                .limit(3) // 3개 레코드로 한정 - 갯수 제한
                .orderBy(
                        new OrderSpecifier(Order.DESC, pathBuilder.get("createdAt")),
                        new OrderSpecifier(Order.ASC, pathBuilder.get("subject")) // 2차정렬
                );
        //.where(boardData.seq.in(2L, 3L, 4L)); // BooleanExpression - Predicate

        List<BoardData> items = query.fetch();
        items.forEach(System.out::println);
    }

    @Test
    void test6() {
        QBoardData qBoardData = QBoardData.boardData;
        JPAQuery<Long> query = jpaQueryFactory.select(qBoardData.seq.sum())
                .from(qBoardData);
        long sum = query.fetchOne();
        System.out.println(sum);
    }

    @Test
    void test5() {
        QBoardData qBoardData = QBoardData.boardData;
        JPAQuery<Tuple> query = jpaQueryFactory.select(qBoardData.subject, qBoardData.content)
                .from(qBoardData); // 칼럼이 2개이상이면 자료형 튜플로 가져와야 함 // select : 영속상태가 아니다?(애는 그냥 일부 항목만...) // selectFrom : 영속상태이다(엔티티그자체를 가져오니까 영속상태)
        //JPAQuery<String> query = jpaQueryFactory.select(qBoardData.subject); // 칼럼이 하나이면 칼럼 자료형으로 대입가능
        List<Tuple> tuples = query.fetch();
        for (Tuple item : tuples) {
            String subject = item.get(qBoardData.subject);
            String content = item.get(qBoardData.content);
            System.out.printf("subject: %s, content: %s\n", subject, content);
        }
    }

    @Test // QueryDsl_fetchJoin() : Fetch 조인(선별적으로 즉시조인)
    void test4() {
        QBoardData boardData = QBoardData.boardData;

        //JPAQueryFactory factory = new JPAQueryFactory(em);
        JPAQueryFactory factory = jpaQueryFactory;
        JPAQuery<BoardData> query = factory
                .selectFrom(boardData)
                .leftJoin(boardData.member)
                .fetchJoin(); // 선별적으로 즉시조인
        List<BoardData> items = query.fetch();
        items.forEach(System.out::println);
    }

    @Test // @EntityGraph : Fetch 조인(선별적으로 즉시조인)
    void test3() {
        List<BoardData> items = boardDataRepository.findBySubjectContaining("제목");
    }

    @Test // JPQL_@Query : Fetch 조인(선별적으로 즉시조인)
    void test2() {
        List<BoardData> items = boardDataRepository.getAllList();
    }

    // 지금은 모든 애노테이션 지연로딩임
    @Test
    void test1() {
        List<BoardData> items = boardDataRepository.findAll(); // 10개의 게시글 가져오기

        for (BoardData item : items) {
            Member member = item.getMember();
            String email = member.getEmail(); // 이때 쿼리가 수행됨
            String password = member.getUserName(); // 이때 쿼리가 수행됨
            System.out.printf("email: %s, password: %s\n", email, password);
        }
    }
}
