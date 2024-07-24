package org.choongang.member.repositories;

import org.choongang.member.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> , QuerydslPredicateExecutor<Member> {
    //프록시 형태로 JPA가 다 만들어 줌
    Member findByEmail(String email); // 이메일로 조회가능하게 메서드를 만들 수 있음

    Page<Member> findByEmailContaining(String keyword, Pageable pageable);

    List<Member> findByEmailContainingAndUserNameContainingOrderByCreatedAtDesc(String key1, String key2);

    @Query("SELECT m FROM Member m WHERE m.email LIKE :k1 AND m.userName LIKE :k2 ORDER BY m.createdAt DESC") // m : Member(엔티티) 별칭 적용한 것
    List<Member> getMembers(@Param("k1") String key1, @Param("k2") String key2);
}
