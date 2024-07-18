package org.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.choongang.config.MvcConfig;
import org.choongang.exam.PostData;
import org.choongang.member.controllers.RequestJoin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@SpringJUnitWebConfig
@ContextConfiguration(classes = MvcConfig.class)
public class Ex01 {

    @Autowired
    private ObjectMapper om;
    @Autowired
    private LocaleResolver localeResolver;

    @Test
    void test1() {
        URI url = UriComponentsBuilder.fromUriString("https://www.naver.com")
                .path("/news/{0}")
                .queryParam("t1", "v1")
                .queryParam("t2", "v2")
                .queryParam("t3", "한글")
                .queryParam("t4", "aa{1}")
                .fragment("hash") // 프래그먼트 : 해시코드
                .encode() // 쿼리스트링 값에 인코딩 되어있는게 있다 // 인코딩을 해준다는 말이 아님 // 전송 가능한 형태(인코딩 상태)인지 체크해주는 것
                .build("true");
                //.build("AAAA", "BBBB");
    }

    @Test
    void test2() {
        RestTemplate restTemplate = new RestTemplate();
        PostData data = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", PostData.class); // getForObject : 바디데이터 받기
        // 기본 : json
        System.out.println(data);
    }

    @Test
    void test3() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String body = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", String.class); // getForObject : 바디데이터 받기
        // 이데이터를 list형태로 바꿔주면 됨 // 오브젝트매퍼??

        // 단일 객체 변환
        PostData data = om.readValue(body, PostData.class);
        System.out.println(data);

        // 복합 데이터 객체 변환 - List, Set, Map
        String itemsBody = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);

        List<PostData> items = om.readValue(itemsBody, new TypeReference<>() {});

        items.forEach(System.out::println);
    }

    @Test
    @DisplayName("JSON형식으로 post 예시")
    void test4() throws Exception{
        RestTemplate restTemplate = new RestTemplate();

        // 커맨드 객체에 값 넣어주기
        RequestJoin form = new RequestJoin();
        form.setEmail("user123@test.org");
        form.setPassword("123456789");
        form.setConfirmPassword("123456789");
        form.setUserName("사용자123");
        form.setAgree(true);
        // 이상태로는 전송이 안됨

        // 제이슨 문자열로 바꾸기
        String params = om.writeValueAsString(form); // 제이슨 문자열 데이터
        System.out.println(params);

        // 콘텐트타입을 설정함으로서 제이슨 형태로 보내준다 서버에게 알려주기
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청데이터 : 요청헤더와 바디를 같이 보낼 수 있음
        // 요청헤더가 꼭 필요 : 콘텐트타입 제이슨인거 알려주기위해서
        HttpEntity<String> request = new HttpEntity<>(params, headers); // 여기에 요청데이터(요청헤더, 바디) 담음

        // 요청 보내기
        String url = "http://localhost:3000/day05/api/member";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response);
    }

    @Test
    @DisplayName("일반 양식 형식으로 전송 - 컨텐츠 타입 : application/x-www-form-urlencoded")
    void test5() {
        RestTemplate restTemplate = new RestTemplate();

        // 요청 바디
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("email", "user998@test.org");
        params.add("password", "123456789");
        params.add("confirmPassword", "123456789");
        params.add("userName", "사용자998");
        params.add("agree", "true");

        HttpHeaders headers = new HttpHeaders(); // 요청 헤더 // 전송하는 데이터의 형식 알려주기 = 컨텐츠 타입
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers); // 여기에 요청데이터(요청헤더, 바디) 담음

        // 바디데이터 변환 안할거임 = String
        // 자세한 정보 받기 위해 ResponseEntity 사용함
        // 요청 보내기
        String url = "http://localhost:3000/day05/member/join";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response);
    }
}
