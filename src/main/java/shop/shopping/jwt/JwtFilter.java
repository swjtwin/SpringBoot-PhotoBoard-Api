package shop.shopping.jwt;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import shop.shopping.service.MemberService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
token 값으로 유효성 검사
OncePerRequestFilter : 매번 들어갈 때 마다 체크 해주는 필터
 */
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {


    private final MemberService memberService;

    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("Authorization : {}" , authorization);


        // Header의 Authorization 값이 비어있으면 Jwt Token을 전송하지 않음 => 로그인 하지 않음
        // 'Bearer '로 시작하지 않으면 => 잘못된 토큰 다음 필터 진행(인증 x)
        if (authorization == null || !authorization.startsWith("Bearer ")){
            log.error("authorization 이 없습니다.");
             filterChain.doFilter(request , response);
            return;
        }

        // 전송받은 값에서 'Bearer ' 뒷부분(Jwt Token) 추출
        String token = authorization.split(" ")[1];

        // 전송받은 Jwt Token이 만료되었으면 => 다음 필터 진행(인증 X)
        if (JwtTokenUtil.isExpired(token , secretKey)) {
            log.error("Token이 만료 되었습니다.");
            filterChain.doFilter(request , response);
            return;
        }


        // username Token에서 꺼내기
        String username = "";

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("USER")));

        // Detail을 넣어준다
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request , response);
    }
}
