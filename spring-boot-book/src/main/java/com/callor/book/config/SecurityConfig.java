package com.callor.book.config;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
지금부터 이 클래스는 Config 설정을 하는 곳이다.
 */
@Configuration
@Slf4j
public class SecurityConfig {

    //비밀번호 암호화를 위한도구
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean       //bean method 는 pj 가 시작될 때 자동으로 실행하라는 표식
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/").permitAll()           //root 접속 아무나
                .antMatchers("/user/login").permitAll() //user/login 아무나
                .antMatchers("/user/join").permitAll()  //user/join 아무나
                //book 으로 시작되는 모든 것은 로그인 해야한다.
                .antMatchers("/book/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/user/login")
                .and()
                .logout();


        return http.build();
    }


}
