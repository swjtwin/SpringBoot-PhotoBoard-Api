package shop.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.shopping.entity.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member , Long> {

    Optional<Member> findByUsername(String usreName);

}
