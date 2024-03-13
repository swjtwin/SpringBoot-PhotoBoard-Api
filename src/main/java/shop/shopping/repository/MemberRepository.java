package shop.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.shopping.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member , Long> {

    Optional<Member> findByEmail(String email);
    // select * from Member where email = ?

//    boolean existsByEmail(String email);
}
