package org.choongang.member.entities;

import jakarta.persistence.*;
import lombok.*;
import org.choongang.board.entities.BoardData;
import org.choongang.global.entities.BaseEntity;
import org.choongang.member.constants.Authority;

import java.util.List;

@Builder // 기본생성자가 private
@Data
@Entity
@NoArgsConstructor @AllArgsConstructor // 빌더도 쓰면서 기본생성자도 쓰게끔함
//@Table(name = "CH_MEMBER") //DB 테이블명 바꾸기
/*
@Table(indexes = {
        @Index(name="idx_created_at_desc", columnList = "createdAt DESC"),
        @Index(name = "uq_email_password", columnList = "email, password", unique = true) // 방향성 기본값 : 오름차순
})
 */
public class Member extends BaseEntity {
    @Id  /* @GeneratedValue(strategy = GenerationType.AUTO) */ @GeneratedValue
    private Long seq; // 래퍼클래스

    @Column(length = 60, nullable = false, unique=true) // 필수항목(널허용x), 유니크제약조건걸기
    private String email;

    @Column(length = 65, nullable = false)
    private String password;

    @Column(length = 40, nullable = false, name = "name") // db칼럼명 name과 매핑
    private String userName;

    //@Lob
    @Transient // 테이블로도 컬럼으로도 추가되지 않음
    private String introduction;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    // enum 타입 매핑
    // 기본값이 ordinal이지만 습관성으로 EnumType.STRING으로 바꿔주기
    private Authority authority;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_seq")
    private MemberProfile profile;

    @ToString.Exclude // ToString 추가 배제
    @OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true) // One : Member // Many : BoarData
    private List<BoardData> items;

    /*
    @CreationTimestamp // insert 시 시간 자동 저장
    private LocalDateTime createdAt;

    @UpdateTimestamp // update 시 시간 자동 저장
    private LocalDateTime modifiedAt;
    */
    //@Temporal(TemporalType.DATE) // 날짜만
    //private Date date;
}
