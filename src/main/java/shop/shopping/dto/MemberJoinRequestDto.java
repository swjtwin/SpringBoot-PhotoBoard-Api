package shop.shopping.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MemberJoinRequestDto {

    private String userName;

    private String password;

    private String nickname;

    private String gender;

}
