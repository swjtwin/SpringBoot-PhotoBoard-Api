package shop.shopping.service;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shop.shopping.constant.ErrorCode;
import shop.shopping.entity.Member;
import shop.shopping.exception.AppException;
import shop.shopping.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder encoder;
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
                .password(encoder.encode(password))
                .nickname(nickname)
                .gender(gender)
                .build();

        memberRepository.save(member);

        return "SUCESS";
    }

    public String login(String username , String password) {

        // username 없음
        Member selectedMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, username + "이 없습니다."));

        // password 틀림
        if (!encoder.matches(selectedMember.getPassword(), password)) { // encoder 매치했을때 비밀번호가 틀렸을시 AppException 처리
            throw new AppException(ErrorCode.INVALID_PASSWORD , "패스워드를 잘못 입력 했습니다");
        }

        return "token 리턴";
    }
}
