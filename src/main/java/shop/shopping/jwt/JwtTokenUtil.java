package shop.shopping.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
/*
    JWT Token 발급
    장점 : 서버는 비밀키만 알고 있으면 되기 때문에 세션 방식과 같이 별도의 인증 저장소가 필요하지 않음 => 서버측 부하 감소
           여러개의 서버를 사용하는 대형 서비스 같은 경우에 접근 권한 관리가 매우 효율적임 => 확장성이 좋음
           Refresh Token까지 활용한다면 더 높은 보안성을 가질 수 있음

    단점 : Payload의 정보(Claim)가 많아질 수록 토큰이 커짐
           중요한 데이터는 넣을 수 없음
           토큰 자체를 탈취당하면 대처가 어려움
           로그아웃 시 JWT 방식은 세션이 없는 stateless 방식이기 때문에 토큰 관리가 어려움
 */
public class JwtTokenUtil {


    public static String createToken(String usernmae, String secretKey , long tokenValidTime) { // secretKey 서명

        // Claim = Jwt Token에 들어갈 정보
        // Claim에 loginId를 넣어 줌으로써 나중에 loginId를 꺼낼 수 있음
        Claims claims = Jwts.claims(); // 일종의 map
        claims.put("username" , usernmae);



        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey) // 서명. 사용할 암호화 알고리즘과 signature 에 들어갈 secretKey 세팅
                .compact()
                ;
    }
}
