package shop.shopping.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import shop.shopping.constant.ErrorCode;
import shop.shopping.dto.MemberJoinRequestDto;
import shop.shopping.dto.MemberLoginRequestDto;
import shop.shopping.exception.AppException;
import shop.shopping.service.MemberService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
    @WithMockUser // Security
    void join() throws Exception {

        String username = "Shinyujin";
        String password = "1q2w3e4r";
        String nickname = "sarangmother";
        String gender   = "men";

        mockMvc.perform(post("/api/v1/members/join")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto(username , password , nickname ,gender)))) // get post 든 경로를 적어주면 된다
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    @WithMockUser
    void join_fail() throws Exception {

        String username = "Shinyujin";
        String password = "1q2w3e4r";
        String nickname = "sarangmother";
        String gender   = "men";

        when(memberService.join(any(),any(),any(),any()))
                .thenThrow(new RuntimeException("해당 유저아이디 는 중복입니다"));

        mockMvc.perform(post("/api/v1/members/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto(username , password , nickname ,gender)))) // get post 든 경로를 적어주면 된다
                .andDo(print())
                .andExpect(status().isConflict());

    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser // 인증되지 않은 사용자를 테스트에서 사용할 때 필요한 어노테이션
    void login_success() throws Exception {

        String username = "Shinyujin";
        String password = "1q2w3e4r";

        when(memberService.login(any(),any()))
                .thenReturn("token"); // Exception 이 안 났기 때문에 token

        mockMvc.perform(post("/api/v1/members/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto(username , password)))) // get post 든 경로를 적어주면 된다
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("로그인 실패 - username없음")
    @WithMockUser // 인증되지 않은 사용자를 테스트에서 사용할 때 필요한 어노테이션
        void login_fail1() throws Exception {

            String username = "Shinyujin";
            String password = "1q2w3e4r";

            when(memberService.login(any(),any()))
                    .thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND, ""));

        mockMvc.perform(post("/api/v1/members/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto(username , password)))) // get post 든 경로를 적어주면 된다
                .andDo(print())
                .andExpect(status().isNotFound());

                // Java로 작성되었으며 Spring 프레임워크를 사용하여 RESTful API 엔드포인트를 테스트하는 단위 테스트의 일부입니다.
                // 구체적으로 andExpect(status().isConflict())는 테스트 중인 엔드포인트의 HTTP 응답 상태가

    }

    @Test
    @DisplayName("로그인 실패 - password틀림")
    @WithMockUser // 인증되지 않은 사용자를 테스트에서 사용할 때 필요한 어노테이션
    void login_fail2() throws Exception {

        String username = "Shinyujin";
        String password = "1q2w3e4r";

        when(memberService.login(any(),any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, ""));

        mockMvc.perform(post("/api/v1/members/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto(username , password)))) // get post 든 경로를 적어주면 된다
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

}
