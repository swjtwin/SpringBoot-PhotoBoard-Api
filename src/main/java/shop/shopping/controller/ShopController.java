package shop.shopping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.shopping.dto.ShopDTO;
import shop.shopping.service.ShopService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor // 롬복(Lombok) 어노테이션으로, 필드 주입을 위한 생성자를 자동으로 생성합니다.
public class ShopController {
    // 생성자 주입
    private final ShopService shopService;

    //회원가입 페이지 출력 요청
    @GetMapping("/shop/save")
    public String saveForm(){

        return "save"; // => templates 폴더의 save.html 을 찾아감
    }


    //회원가입 완료시 로그인 페이지 이동
    //HTTP POST 요청에 대한 매핑 /shop/save 경로로 POST 요청이 오면 save 메서드가 호출
    @PostMapping("/shop/save")
    public String save(@ModelAttribute ShopDTO shopDTO) {
        // save.html 에서 name값으로 설정한 값을 가져와서 String userId 넣어준다

        System.out.println("ShopController.save");
        System.out.println("shopDTO = " + shopDTO);
        shopService.save(shopDTO); //@ModelAttribute 어노테이션을 사용하여 HTTP 요청의 데이터를 수신하고, shopService.save(shopDTO)를 호출하여 회원 정보
        return "login";
    }


    // 로그인 페이지
    @GetMapping("/shop/login")
    public String lgonForm(){

        return "login"; // => templates 폴더의 login.html 을 찾아감
    }

    @PostMapping("shop/login")
    public String login(@ModelAttribute ShopDTO shopDTO, HttpSession session) {
        ShopDTO loginResult = shopService.login(shopDTO);
        if(loginResult != null){
            // login 성공
            session.setAttribute("loginId", loginResult.getUserId());
            return "main";
        }else{
            // login 실패
            return "login";
        }

    }


}
