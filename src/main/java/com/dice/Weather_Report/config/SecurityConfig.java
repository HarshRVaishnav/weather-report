package com.dice.Weather_Report.config;


import com.dice.Weather_Report.filter.JwtFilter;
import com.dice.Weather_Report.service.impl.CustomUserDetailService;
import com.dice.Weather_Report.service.impl.InvalidUserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// in this class we are having custom implementation of customAuthenticationProvider and securityFilterChain
@Configuration
public class SecurityConfig
{

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    private  InvalidUserEntry invalidUserEntry;

    @Autowired
    private JwtFilter jwtFilter;

    //  it is customised to define session management policy n authorization rules for roleBased
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/register/**", "/login").permitAll()
                .antMatchers("/forecastSummery/**", "/hourlyForecast/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(invalidUserEntry)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder customPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider CustomAuthenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(customPasswordEncoder());

        return authenticationProvider;
    }

}
