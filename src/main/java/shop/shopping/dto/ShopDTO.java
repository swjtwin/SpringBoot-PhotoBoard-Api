package shop.shopping.dto;

import lombok.*;
import shop.shopping.entity.ShopEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// DTO 필드값 초기화 해준다고 생각하면 됨 -> 이거 토대로 entity에서 테이블 생성 및 컬럼 생성
public class ShopDTO {
    private Long id;
    private String userId;
    private String userPwd;
    private String userEmail;
    private String userTel;
    private String userName;
    private String gender;

    public static ShopDTO toShopDTO(ShopEntity shopEntity) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shopEntity.getId());
        shopDTO.setUserId(shopEntity.getUserId());
        shopDTO.setUserPwd(shopEntity.getUserPwd());
        shopDTO.setUserEmail(shopEntity.getUserEmail());
        shopDTO.setUserTel(shopEntity.getUserTel());
        shopDTO.setUserName(shopEntity.getUserName());
        shopDTO.setGender(shopEntity.getGender());
        return shopDTO;


    }
}
