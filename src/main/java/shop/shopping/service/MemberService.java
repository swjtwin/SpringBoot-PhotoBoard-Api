package shop.shopping.service;

import shop.shopping.dto.MemberSignUpRequestDto;

public interface MemberService {

    // 회원가입
    public Long signUp(MemberSignUpRequestDto requestDto) throws Exception;
}
