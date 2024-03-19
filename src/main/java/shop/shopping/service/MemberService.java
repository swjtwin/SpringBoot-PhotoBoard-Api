package shop.shopping.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shop.shopping.constant.ErrorCode;
import shop.shopping.entity.Member;
import shop.shopping.exception.AppException;
import shop.shopping.jwt.JwtTokenUtil;
import shop.shopping.repository.MemberRepository;

import javax.annotation.PostConstruct;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {

    private final BCryptPasswordEncoder encoder;

    private final MemberRepository memberRepository;
    @Value("${jwt.token.key}")
    private String secretKey;
    private Long tokenValidTime = 1000 * 60 * 60L; //토큰 유효시간 설정

    //secretkey를 미리 인코딩 해줌.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

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
        log.info("selectedPw:{} pw:{}", selectedMember.getPassword(), password);
        if (!encoder.matches( password, selectedMember.getPassword())) { // encoder 매치했을때 비밀번호가 틀렸을시 AppException 처리
            throw new AppException(ErrorCode.INVALID_PASSWORD , "패스워드를 잘못 입력 했습니다");
        }

        String token = JwtTokenUtil.createToken(selectedMember.getUsername(), secretKey ,tokenValidTime);

        // 앞에서 Exception 안났으면 토큰 발행
        return token;
    }
}
