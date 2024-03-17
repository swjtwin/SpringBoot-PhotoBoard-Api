package shop.shopping.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.shopping.constant.ErrorCode;
import shop.shopping.entity.Member;
import shop.shopping.exception.AppException;
import shop.shopping.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String join(String username, String password , String nickname , String gender) {

        // userName 중복 check
        memberRepository.findByUsername(username)
                .ifPresent(member -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, username + "는 이미 있습니다.");

                });


        // 저장
        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .gender(gender)
                .build();

        memberRepository.save(member);

        return "SUCESS";
    }
}
