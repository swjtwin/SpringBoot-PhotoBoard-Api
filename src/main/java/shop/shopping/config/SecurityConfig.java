package shop.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Bean을 등록할 때 싱글톤(Singleton)이 되도록 보장 스프링 컨테이너에서 Bean을 관리할 수 있게 됨
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable() //프론트엔드가 별도로 존재하여 rest Api로 구성한다고 가정
                .csrf().disable() // 서버에 인증정보를 저장하지 않기에 csrf를 사용하지 않음 Cross Site Request Forgery
                .cors().disable() // STATELESS 라 cors는 disable
                .authorizeHttpRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정
                // antMatchers = 특정 리소스에 대해서 권한 설정
                .antMatchers("api/v1/members/join" , "api/v1/members/login").permitAll() // 회원가입 , 로그인 인증절차 없이 허용가능
//                .antMatchers(HttpMethod.POST,"api/v1/**").authenticated() // 위 두가지를 제외한 모든 포스트 요청은 인증필요

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                // Session 기반의 인증기반을 사용하지 않고 추후 JWT를 이용하여서 인증 예정
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }


}
