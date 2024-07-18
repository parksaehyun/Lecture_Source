package org.choongang.member.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.choongang.config.MvcConfig;
import org.choongang.member.controllers.RequestJoin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@ContextConfiguration(classes = MvcConfig.class)
public class ApiMemberControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ApiMemberController apiMemberController;
    // 왜 이건 생성자 매개변수로 안하징

   // @Autowired
    //private WebApplicationContext ctx;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiMemberController).build();
        //mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test // 요청 보내기
    void test1() throws Exception {

        // 잭슨데이터 바인드에 포함된 핵심적인 클래스 자바객체를 json으로 바꿔줌
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        RequestJoin form = new RequestJoin();
        form.setEmail("user100@test.org");
        form.setPassword("123456789");
        form.setConfirmPassword("123456789");
        form.setUserName("사용자100");
        form.setAgree(true);

        //System.out.println(json);
        // 자바객체를 json으로 바꿔줌 아직 요청 안보냄
        // 왜 바꿈? restAPI가 json형식이기 때문에...?

        // 이제 요청을 json방식으로 보낼거임
        String json = objectMapper.writeValueAsString(form);
        mockMvc.perform(
                post("/api/member")
                        .contentType(MediaType.APPLICATION_JSON) // 요청 헤더
                        .content(json) // 요청 바디
        ).andDo(print())
        .andExpect(status().isCreated()); // 응답코드 쳌

        /* // 이건 json방식 아님
        // Content-Type : application/x-www-form-url-urlencoded // 기본 Content-Type 임
        // urlencoded : 한글을 16진수로 바꾸어 줌
        // 이름=값&이름=값... 요런 형식임
        // post방식으로 요청 보내기
        mockMvc.perform(post("/api/member")
                .param("email", "user99@test.org") // 콘텐트 타입 뭐라구요...
                .param("password", "123456789")
                .param("confirmPassword", "123456789")
                .param("userName", "사용자99"))
                .andDo(print());
        // 요청을 보내고 응답한 내용을 자세히 확인 가능
         */
    }

    @Test
    void test2() throws Exception {
        mockMvc.perform(get("/api/member/list")).andDo(print()).andExpect(status().isOk());
    }
}
