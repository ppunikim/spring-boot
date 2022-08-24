package com.callor.book.config;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/* @Configuration
지금부터 이 클래스는 Config 설정을 하는 곳이다.
즉, *-context.xml 파일의 설정을 대신하는 클래스 선언이다.
 */
@Configuration
@Slf4j
public class SecurityConfig {

    //비밀번호 암호화를 위한도구
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
    @Bean
    이 Annotation 이 부착된 method 는 자동으로 실행돼
    bean 들을 생성하는 일을 수행한다.
     */
    @Bean  //bean method 는 pj 가 시작될 때 자동으로 실행하라는 표식, User 의 Request 를 Security 를 통해 이루어지도록 하는 Bean 이다.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/user/login").permitAll() //user/login 아무나
                .antMatchers("/user/join").permitAll()  //user/join 아무나
                .antMatchers("/").permitAll()           //root 접속 아무나

                //book 으로 시작되는 모든 것은 로그인 해야한다.
                .antMatchers("/book/**").authenticated()

                //위에서 지정한 이외의 패턴일 경우,
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/user/login")
                .and()
                .logout();


        return http.build();
    }

    /*
        인증을 수행하는 메서드이다.
     */
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }



    /*  /static/, /js/, /css/, /upload/, /files/ 이러한 폴더는
        Controller 를 향하지 않고, 바로 response 를 하도록 선언하는 부분.
        Legacy 에서 resource mapping 으로 설정하는 부분을 여기에서 선언.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        //아래에 저장된 pattern 요청이 들어오면 security 를 거치지 않고 바로 통과시켜라.
        return (web) -> web.ignoring().antMatchers("/static/**","/js/**","/upload/**");
    }



}
