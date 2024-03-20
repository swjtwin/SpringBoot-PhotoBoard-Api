package shop.shopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/boards")
@RequiredArgsConstructor // final이나 @NonNull으로 선언된 필드만을 파라미터로 받는 생성자를 생성
public class BoardController {

    @PostMapping("board")
    public String board(){
        return "게시판 목록 조회";
    }


}
