package shop.shopping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.shopping.dto.ShopDTO;
import shop.shopping.entity.ShopEntity;
import shop.shopping.repository.ShopRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ShopService {
    // @Service // Spring에서 이 클래스를 빈으로 관리하기 위한 어노테이션
    private final ShopRepository shopRepository;
        public void save(ShopDTO shopDTO) {
            //1. dto -> entity 객체로 변환
            //2. repository의 save 메서드 호출
            ShopEntity shopEntity = ShopEntity.toShopEntity(shopDTO);
            shopRepository.save(shopEntity);
            //repository의 save 메서드 호출 (조건. entity객체를 넘겨줘야 함)

    }

    public ShopDTO login(ShopDTO shopDTO) {
            /*
            *  1.  회원이 입력한 아이디로 DB에서 조회를 함
            *  2.  DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
            * */

        Optional<ShopEntity> byUserId = shopRepository.findByUserId(shopDTO.getUserId());
        if (byUserId.isPresent()){
            // 조회 결과 있다(해당 아이디 를 가진 회원 정보가 있다)
            ShopEntity shopEntity = byUserId.get();
            if (shopEntity.getUserPwd().equals(shopDTO.getUserPwd())) {
                // 비밀번호 일치하는 경우
                // entity -> dto 변환 후 리턴
                ShopDTO dto = ShopDTO.toShopDTO(shopEntity);
                return dto;
            }else{
                // 비밀번호 불일치하는 경우
                return null;
            }
        }else{
            // 조회 결과 없다( 해당 아이디 를 가진 회원 정보가 없다)
            return null;
        }


    }
}
