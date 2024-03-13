package shop.shopping.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.shopping.constant.Role;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String email; // 이메일

    private String password; // 패스워드

    private String nickname; // 닉네임

    // 권한
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String email, String password, String nickname , Role role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;

    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }
}
