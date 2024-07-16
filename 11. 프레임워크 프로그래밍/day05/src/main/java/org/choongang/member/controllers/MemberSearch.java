package org.choongang.member.controllers;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MemberSearch {
    // 회원을 조회할 수 있는 커맨드객체
    // 언제가입했는지, 종료했는지 조회

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate sDate; // 검색 시작일

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate eDate; // 검색 종료일
}
