package org.choongang.member.controllers;

import org.choongang.config.MvcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringJUnitWebConfig
@ContextConfiguration(classes = MvcConfig.class)
public class MemberControllerTest {

    @Autowired
    private WebApplicationContext ctx;
    private MockMvc mockMvc; //

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    void test1() throws Exception {
        mockMvc.perform(
                post("/member/join")
                        .header("appKey", "1234") // 요청 헤더 한정
                        .contentType("application/json") // 요청 콘텐트타입 한정
        ) // 이렇게 하면 요청메서드가 간다
                .andDo(print()); // 요청헤더, 바디 등등 요청에 대한 자세한 정보가 담겨있다
    }

    @Test
    void test2() throws Exception {
        mockMvc.perform(
                get("/member/join")
                        .header("Accept-Language", Locale.KOREAN.getLanguage())
                )
                .andDo(print());
    }
}
