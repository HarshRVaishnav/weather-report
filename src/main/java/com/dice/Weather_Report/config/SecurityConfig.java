package com.dice.Weather_Report.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;



import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
public class SecurityConfig
{

//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(PathRequest.to("/service/message")).hasRole("USER")
//                        .requestMatchers(PathRequest.to("/product/details", "/product/details/price")).hasAnyRole("ADMIN")
//                        .requestMatchers(PathRequest.to("/wallet/balance")).hasRole("USER")
//                        .requestMatchers(PathRequest.to("/welcome")).authenticated()
//                        .requestMatchers(PathRequest.to("/register")).permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults());
//        return http.build();
//    }
//
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
