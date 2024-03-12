package shop.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import shop.shopping.dto.ShopDTO;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "customer")
public class ShopEntity {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto
    private Long id;

    @Column(unique = true) //unique 제약조건 추가
    private String userId;

    @Column
    private  String userPwd;

    @Column
    private  String userEmail;

    @Column
    private  String userTel;

    @Column
    private  String userName;

    @Column
    private  String gender;

    public static ShopEntity toShopEntity(ShopDTO shopDTO){
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(shopDTO.getId());
        shopEntity.setUserId(shopDTO.getUserId());
        shopEntity.setUserPwd(shopDTO.getUserPwd());
        shopEntity.setUserEmail(shopDTO.getUserEmail());
        shopEntity.setUserTel(shopDTO.getUserTel());
        shopEntity.setUserName(shopDTO.getUserName());
        shopEntity.setGender(shopDTO.getGender());
        return shopEntity;
    }



}
