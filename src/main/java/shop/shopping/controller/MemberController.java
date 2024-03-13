package shop.shopping.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.shopping.dto.MemberJoinRequestDto;
import shop.shopping.service.MemberService;

@RestController
@RequestMapping(value = "/api/v1/members")
@RequiredArgsConstructor // final이나 @NonNull으로 선언된 필드만을 파라미터로 받는 생성자를 생성
public class MemberController {


    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinRequestDto dto) {
        return ResponseEntity.ok().body("회원가입 성공 했습니다.");
    }
}
