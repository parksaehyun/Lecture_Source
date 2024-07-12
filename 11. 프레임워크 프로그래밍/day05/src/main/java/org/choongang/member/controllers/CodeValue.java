package org.choongang.member.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 모든변수를 생성자 매개변수로 넣어주기?
public class CodeValue {
    private String code;
    private String value;
}
