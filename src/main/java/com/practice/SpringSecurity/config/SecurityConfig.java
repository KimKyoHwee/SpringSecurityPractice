package com.practice.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //해당 클래스를 config등록
@EnableWebSecurity //security 설정
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{ //메소드명 자유

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login").permitAll()  //모두 접근 가능
                        .requestMatchers("/admin").hasRole("ADMIN")  //ADMIN role 가져야 접근가능
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") //여러가지 role 지정
                        .anyRequest().authenticated()  //로그인만 진행하면 접근가능(나머지 경로들)
                );

        return http.build();
    }
}
