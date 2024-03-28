package shop.shopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/boards")
@RequiredArgsConstructor // final이나 @NonNull으로 선언된 필드만을 파라미터로 받는 생성자를 생성
public class BoardController {

    @PostMapping("")
    public ResponseEntity<String> board(Authentication authentication){
        return ResponseEntity.ok().body(authentication.getName() + "님의 게시판 목록 조회가 완료 되었습니다.");
    }

    @GetMapping("main")
    public String boardMain() {
        return "게시판 메인페이지 입니다";
    }


}
