package shop.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .cors().disable()
                .csrf().disable()  // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증하므로 세션은 필요없으므로 생성안함.
                .and()
                .authorizeRequests() //다음 리퀘스트에 대한 사용권한 체크
                .antMatchers("/member/login").permitAll() // 로그인 주소는 누구나 접근가능
                .antMatchers("/member/join").permitAll() // 회원가입 누구나 접근가능
                .antMatchers("/member").hasRole("USER") // 그 외에 나머지 요청은 모두 인증된 회원만 접근 가능
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/file/**",
            "/image/**",
            "/swagger/**",
            "/swagger-ui/**",
            "/mariadb/**"
    };

    // 정적인 파일 요청에 대해 무시
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }
}
