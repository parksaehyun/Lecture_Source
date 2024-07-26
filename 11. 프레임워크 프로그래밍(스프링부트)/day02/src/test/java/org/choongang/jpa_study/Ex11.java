package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.choongang.board.entities.BoardData;
import org.choongang.board.entities.HashTag;
import org.choongang.board.repositories.BoardDataRepository;
import org.choongang.board.repositories.HashTagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class Ex11 {
    @Autowired
    private BoardDataRepository boardDataRepository;

    @Autowired
    private HashTagRepository hashTagRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void init() {
        // 태그 5개 만들기
        List<HashTag> tags = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> HashTag.builder()
                        .tag("태그" + i)
                        .build()).toList();

        hashTagRepository.saveAllAndFlush(tags); // db저장

        // 게시글 5개 만들기
        List<BoardData> items = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> BoardData.builder()
                        .subject("제목" + i)
                        .content("내용" + i)
                        //.tags(tags)
                        .build()).toList();
        boardDataRepository.saveAllAndFlush(items); // db저장

        em.clear();

    }

    @Test // 게시글 1개 조회하고 그걸바탕으로 태그조회할거임
    void test1() {
        BoardData item = boardDataRepository.findById(1L).orElse(null);
        //List<HashTag> tags = item.getTags();
        //tags.forEach(System.out::println);
    }

    @Test // 태그 1개 조회하고 그걸바탕으로 게시글조회할거임
    void test2() {
        HashTag tag = hashTagRepository.findById("태그2").orElse(null);
        //List<BoardData> items = tag.getItems(); // 게시글 가져옴
        //items.forEach(System.out::println);
    }
}
