package com.practice.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //해당 클래스를 config등록
@EnableWebSecurity //security 설정
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){  //메소드명 자유
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{ //메소드명 자유

        //인가작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()  //모두 접근 가능
                        .requestMatchers("/admin").hasRole("ADMIN")  //ADMIN role 가져야 접근가능
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") //여러가지 role 지정
                        .anyRequest().authenticated()  //로그인만 진행하면 접근가능(나머지 경로들)
                );
        //로그인 페이지 redirection 설정, html 로그인 페이지에서 넘어온 데이터를 security가 받아서 로그인 처리 진행
        http
                .formLogin((auth)->auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()   //이 경로로 아무나 들어올 수 있다.
                );
        //csrf : spring security에 자동으로 설정되어 있는사이트 위변조 방지 설정(csrf토큰도 같이 보내주어야 하므로 일단 disable)
//        http
//                .csrf((auth)->auth.disable());
        http //로그아웃은 GET요청이기 때문에 csrf 토큰 검정이 안돼서 설정추가
                .logout((auth)->auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));
        http  //다중 로그인 설정
                .sessionManagement((auth)->auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));
        http  //세션 고정 보호
                .sessionManagement((auth)->auth
                        .sessionFixation().changeSessionId());
        return http.build();
    }
}
