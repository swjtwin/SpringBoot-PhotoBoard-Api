package shop.shopping.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberJoinRequestDto {

    private String username;

    private String password;

}
