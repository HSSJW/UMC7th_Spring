package umc7.spring.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity  //Spring Security설정을 활성화 시킴
@Configuration //
public class SecurityConfig {

    @Bean //SecurityFilterChain을 정의               http객체를 통해 다양한 보안 설정을 구성
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests //http요청 접근 제어설정
                        .requestMatchers("/", "/home", "/signup","/members/signup", "/css/**").permitAll() //특정 url에 대한 접근 권한 설정
                        .requestMatchers("/admin/**").hasRole("ADMIN") //hasRole : ADMIN역할을 가진 사용자만 접근가능하게 제한
                        .anyRequest().authenticated()  // 그 외 모든 요청에 대해 인증을 요구
                )
                .formLogin((form) -> form
                        .loginPage("/login") // 커스텀 로그인 페이지를 /login경로로 지정
                        .defaultSuccessUrl("/home", true) //로그인 성공 시 /home으로 redirect
                        .permitAll() //permitAll : 인증 없이 접근가능한 경로 지정 
                )
                .logout((logout) -> logout //로그아웃 처리
                        .logoutUrl("/logout") //이 경로로 로그아웃을 처리
                        .logoutSuccessUrl("/login?logout") //로그아웃 성공시 이 경로로 redirect
                        .permitAll() 
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //비밀번호 암호화
        return new BCryptPasswordEncoder(); //암호화를 위해 사용
    }
}
