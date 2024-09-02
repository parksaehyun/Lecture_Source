package org.choongang.member.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.choongang.member.entities.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="Member", description = )
@RestController
@RequestMapping('api/v1/member')
public class MemberController {

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return ResponseEntity.status(HttpStatus.created).body(new Member());
    }
}
