package org.choongang.member.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
//@Table("CH_MEMBER") // 클래스명과 DB테이블명이 다른경우 이렇게 알려주기
public class Member {
    @Id
    //@Column("ID")
    private Long seq;
    //private long seq;
    private String email;
    private String password;
    private String userName;
    private LocalDateTime regDt;
}
