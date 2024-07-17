package org.choongang.member.api.controllers;

import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.choongang.member.services.JoinService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@RestController // 출력이 모델엔뷰, 문자열이 아닌 자바 객체 반환
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class ApiMemberController {

    private final MemberMapper mapper;
    private final JoinService joinService;

    @PostMapping // POST /api/member
    public ResponseEntity<Object> join(@RequestBody RequestJoin form) {
        //log.info(form.toString());

        joinService.process(form); // @ResponseBody RequestJoin form : 요청 데이터 json으로 받는다?

        // 응답코드 201, 출력 바디x
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/info/{email}")
    public Member info(@PathVariable("email") String email) {
        Member member = mapper.get(email); // 회원조회

        return member;
    }

    @GetMapping("/list")
    public  ResponseEntity<List<Member>> list() {
        // 10명의 회원추가하고 목록형태로 출력할거임
        List<Member> members = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Member.builder()
                        .email("user" + i + "@test.org")
                        .password("12345678")
                        .userName("사용자" + i)
                        .regDt(LocalDateTime.now())
                        .build())
                .toList();

        //return members;

        HttpHeaders headers = new HttpHeaders(); // 응답 헤더
        headers.add("t1", "v1");
        headers.add("t2", "v2");

        return new ResponseEntity<>(members, headers, HttpStatus.OK);

        //return ResponseEntity.status(HttpStatus.OK).body(members); // 상태코드와 출력 데이터를 담음
    }

    @GetMapping(path="/test", produces = "text/html;charset=UTF-8")
    public String test() {
        // 응답헤더 Content-Type : application/json
        // path="/test", produces = MediaType.TEXT_PLAIN_VALUE : 응답헤더 콘텐트 타입 변경 // 이거 안먹음
        // @GetMapping(path="/test", produces = "text/html;charset=UTF-8") : 응답헤더 콘텐트 타입 변경
        return "안녕하세요!";
    }

    @GetMapping("/test2")
    public void test2() {
        log.info("test2...");
    }
}
