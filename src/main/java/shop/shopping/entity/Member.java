package shop.shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.shopping.constant.Role;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자를 생성
@Getter
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;  // 로그인 아이디

    private String password;  // 비밀번호

    private String nickname;  // 닉네임

    private String gender;    // 성별

    private Integer receivedLikeCnt; // 유저가 받은 좋아요 개수 (본인 제외)

    @Enumerated(EnumType.STRING)
    private Role role; // 권한

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Board> boards;     // 작성글

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Like> likes;       // 유저가 누른 좋아요

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Comment> comments; // 댓글

}
