package shop.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.shopping.entity.ShopEntity;

import java.util.Optional;

// ShopRepository 인터페이스 파일에서 <ShopEntity, Long> 들어가는 ShopEntity 인자 값은 ShopRepository 여기서 어떤 entity파일을 다룰것이냐
// 두번째 인자값은 ShopEntity 파일에서 pk 값에 타입은 무엇인지
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
    // 아이디로 회원 정보 조회 (select * from shop_table where user_id=?)  -> findByUserId
    Optional<ShopEntity> findByUserId(String userId);
}
