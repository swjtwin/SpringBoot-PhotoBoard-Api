package shop.shopping.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.shopping.entity.Member;
import shop.shopping.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String join(String userName, String password , String nickname , String gender) {

        // userName 중복 check
        memberRepository.findByUsername(userName)
                .ifPresent(member -> {
                    throw new RuntimeException(userName + "는 이미 있습니다.");
                });


        // 저장
        Member member = Member.builder()
                .userName(userName)
                .password(password)
                .nickname(nickname)
                .gender(gender)
                .build();

        memberRepository.save(member);

        return "SUCESS";
    }
}
