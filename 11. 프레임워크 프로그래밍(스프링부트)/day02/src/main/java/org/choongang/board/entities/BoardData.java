package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.global.entities.BaseEntity;
import org.choongang.member.entities.Member;

@Data
@Builder // 기본생성자 private
@Entity
@NoArgsConstructor @AllArgsConstructor // 기본생성자 접근 가능하게
public class BoardData extends BaseEntity {
    @Id @GeneratedValue // 자동으로 시퀀스객체 만들기
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mSeq")
    private Member member;
    // Many : BoardData  // One : Member
    // @ManyToOne을 쓰면 자동으로 테이블에 외래키 이름이 `엔티티명_기본키` 생성됨
    // 외래키 : Member_seq

    @Column(nullable = false) // 낫널제약조건
    private String subject;

    @Lob // 여러개의 데이터인 CLOB
    private String content;

    //@ManyToMany // 게시글 하나에 여러개의 tag있을 수 있으니 List
    //private List<HashTag> tags;
}
