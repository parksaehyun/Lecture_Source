package org.choongang.member.api.controllers;

import jakarta.validation.Valid;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.global.Utils;
import org.choongang.global.exceptions.BadRequestException;
import org.choongang.global.exceptions.CommonException;
import org.choongang.global.rests.JSONData;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.choongang.member.services.JoinService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class ApiMemberController {

    private final MemberMapper mapper;
    private final JoinService joinService;
    private final Utils utils;

    @PostMapping // POST /api/member
    public ResponseEntity join(@Valid @RequestBody RequestJoin form, Errors errors) {
        // @RequestBody : 요청쪽 바디(데이터)
        // 커맨드객체에 제이슨 형태로 넣어줌 -> 요청을 제이슨 형태로 보내야 한다

        //log.info(form.toString());

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessage(errors));
        } // 2번 // 커맨드 에러 / 커맨드 객체 검증 에러

        if (errors.hasErrors()) {
            //errors.getFieldErrors().forEach(System.out::println); // 필드별 전체 에러 정보
            //errors.getGlobalErrors().forEach(System.out::println); // 커맨드 객체 자체 에러 정보(reject(...)..)

            return ResponseEntity.badRequest().build();
        }

        /*
        boolean result = false;
        if(!result) {
            throw new BadRequestException("예외 테스트!");
        } // 커맨드에러 아닌 일반적인 예외  1번
           */

        joinService.process(form); // @ResponseBody RequestJoin form : 요청 데이터 json으로 받는다?

        // 응답 코드 201, 출력 바디 X
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/info/{email}")
    public JSONData info(@PathVariable("email") String email) {
        // Content-Type: application/json

        Member member = mapper.get(email); // 회원조회

        return new JSONData(member);
    }

    @GetMapping("/list")
    public ResponseEntity<JSONData> list() {
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

        //return new ResponseEntity<>(members, headers, HttpStatus.OK);
        //return ResponseEntity.status(HttpStatus.OK).body(members); // 상태코드와 출력 데이터를 담음
        return new ResponseEntity<>(new JSONData(members), headers, HttpStatus.OK);
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
        log.info("test2....");
    }
/* // 지금 제이슨 형태로 응답하는 에러는 html로 응답하면 좀...에러도 제이슨형태로 응답하게 해주자
    @ExceptionHandler(Exception.class) // 예외 받기
    public ResponseEntity<JSONData> errorHandler(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500으로 고정

        // 우리가 정의한 예외는 다양한 응답코드가 있음
        // instance of 로 출처 쳌 하고 응답코드 가져와서 응답코드 내보내기
        if (e instanceof CommonException commonException) {
            status = commonException.getStatus(); // 응답코드 가져오기
        }

        JSONData jsonData = new JSONData(); // 실패시
        jsonData.setSuccess(false);
        jsonData.setMessage(e.getMessage());
        jsonData.setStatus(status);

        e.printStackTrace();

        return ResponseEntity.status(status).body(jsonData);
    }

 */
}