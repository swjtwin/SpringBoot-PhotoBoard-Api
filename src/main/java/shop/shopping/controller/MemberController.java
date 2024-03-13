package shop.shopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.shopping.dto.MemberSignUpRequestDto;
import shop.shopping.repository.MemberRepository;
import shop.shopping.service.MemberService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    // 회원가입
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long join(@Valid @RequestBody MemberSignUpRequestDto request) throws Exception {
        return memberService.signUp(request);
    }
}