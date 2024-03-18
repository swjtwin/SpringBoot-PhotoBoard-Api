package shop.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable() // 서버에 인증정보를 저장하지 않기에 csrf를 사용하지 않음 Cross Site Request Forgery
                .cors().disable() // STATELESS 라 cors는 disable
                .authorizeHttpRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정
                // antMatchers = 특정 리소스에 대해서 권한 설정
                .antMatchers("/api/**").permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용가능
                .antMatchers("api/v1/members/join" , "api/v1/members/login").permitAll()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                // Session 기반의 인증기반을 사용하지 않고 추후 JWT를 이용하여서 인증 예정
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }


}
