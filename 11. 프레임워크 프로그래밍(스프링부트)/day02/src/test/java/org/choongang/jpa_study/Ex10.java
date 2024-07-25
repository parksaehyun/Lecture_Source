package org.choongang.jpa_study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.choongang.member.constants.Authority;
import org.choongang.member.entities.Member;
import org.choongang.member.entities.MemberProfile;
import org.choongang.member.repositories.MemberProfileRepository;
import org.choongang.member.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class Ex10 {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberProfileRepository memberProfileRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void init() {
        // 프로필 등록
        MemberProfile profile = MemberProfile.builder()
                .profileImage("이미지")
                .status("상태")
                .build();

        // 프로필 먼저 db에 먼저 넣어주고 member에 할당할거임
        profile = memberProfileRepository.saveAndFlush(profile);

        Member member = Member.builder()
                .email("user01@test.org")
                .password("12345678")
                .userName("사용자01")
                .authority(Authority.USER)
                .profile(profile) // 이거 안넣으면 외래키 값 안넣어짐
                .build();
        memberRepository.saveAndFlush(member);

        em.clear(); // 1차캐싱때문에 쿼리 안보이니까 쿼리보기 위해 일부러 영속성에서 비움
    }

    @Test
    void test1() { // 회원쪽에서 프로필 조회
        Member member = memberRepository.findById(1L).orElse(null);

    }

    @Test
    void test2() { // 프로필쪽에서 회원조회
        MemberProfile profile = memberProfileRepository.findById(1L).orElse(null);
        Member member = profile.getMember();
        System.out.println(member);
    }
}
