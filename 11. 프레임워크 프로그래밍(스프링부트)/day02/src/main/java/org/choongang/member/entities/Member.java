package org.choongang.member.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.choongang.member.constants.Authority;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
//@Table(name = "CH_MEMBER") //DB 테이블명 바꾸기
/*
@Table(indexes = {
        @Index(name="idx_created_at_desc", columnList = "createdAt DESC"),
        @Index(name = "uq_email_password", columnList = "email, password", unique = true) // 방향성 기본값 : 오름차순
})
 */
public class Member {
    @Id  /* @GeneratedValue(strategy = GenerationType.AUTO) */ @GeneratedValue
    private Long seq; // 래퍼클래스
    private String email;
    private String password;
    private String userName;

    //@Lob
    @Transient // 테이블로도 컬럼으로도 추가되지 않음
    private String introduction;

    @Enumerated(EnumType.STRING)
    // enum 타입 매핑
    // 기본값이 ordinal이지만 습관성으로 EnumType.STRING으로 바꿔주기
    private Authority authority;

    @CreationTimestamp // insert 시 시간 자동 저장
    private LocalDateTime createdAt;

    @UpdateTimestamp // update 시 시간 자동 저장
    private LocalDateTime modifiedAt;
}
