package org.choongang.jpa_study;

import org.choongang.member.constants.Authority;
import org.choongang.member.entities.Member;
import org.choongang.member.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex05 {

    @Autowired
    private MemberRepository memberRepository;
    // 프록시로 되어 있음
    // 트랜잭셔널 내포되어 있음

    @BeforeEach
    void init() {
        List<Member> members = IntStream.rangeClosed(1, 10)
                .mapToObj( i -> Member.builder()
                        .email("user" + i + "@test.org")
                        .password("123456789")
                        .userName("사용자" + i)
                        .authority(Authority.USER)
                        .build()).toList();

        memberRepository.saveAllAndFlush(members); // 영속성 저장 + db저장

        /*
        // 10명의 회원 추가
        for (int i = 1; i <= 10; i++) {
        // 1명의 회원 추가
        Member member = Member.builder()
                .email("user" + i + "@test.org")
                .password("123456789")
                .userName("사용자" + i)
                .authority(Authority.USER)
                .build();

        memberRepository.save(member);
        }

        memberRepository.flush();
         */

        //.save(member); // 영속성 상태에 넣어주기
        //memberRepository.flush(); // db에 영구반영

        //memberRepository.saveAndFlush(member); // 영속성에 넣어주기 + db영구반영

        //member.setUserName("(수정)사용자");
    }

    @Test
    void test1() {
        Member member = memberRepository.findById(1L).orElse(null);
        System.out.println(member);

        member.setUserName("(수정)사용자");

        memberRepository.save(member);

        Member member2 = memberRepository.findById(2L).orElse(null);
        System.out.println(member2);

        memberRepository.delete(member2);
        memberRepository.flush();
    }

    @Test
    void test2() {
        List<Member> members = memberRepository.findAll(); // 전체 가져오기
        members.forEach(System.out::println);
    }
}
