package shop.shopping.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import shop.shopping.dto.MemberJoinRequestDto;
import shop.shopping.service.MemberService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@MockBean(JpaMetamodelMappingContext.class)
public class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;
    // MockMvc 실제 서버에 배포하지 않아도 MVC 환경을 만들어 요청 및 전송 응답기능을 제공해주는 유틸리티 클래스
    // Test로 많이 사용

    @MockBean
    MemberService memberService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void join() throws Exception {

        String userName = "Shinyujin";
        String password = "1q2w3e4r";
        String nickname = "sarangmother";
        String gender   = "men";

        mockMvc.perform(post("/api/v1/members/join")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto(userName , password , nickname ,gender)))) // get post 든 경로를 적어주면 된다
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    void join_fail() throws Exception {

        String userName = "Shinyujin";
        String password = "1q2w3e4r";
        String nickname = "sarangmother";
        String gender   = "men";

        mockMvc.perform(post("/api/v1/members/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto(userName , password , nickname ,gender)))) // get post 든 경로를 적어주면 된다
                .andDo(print())
                .andExpect(status().isConflict());

    }

}
