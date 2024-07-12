package org.choongang.member.controllers;

import lombok.Data;

@Data // 중첩된 커맨드 객체
public class Address {
    private String zipCode;
    private String address;
    private String addressSub;
}
