package com.dice.Weather_Report.controller;


import com.dice.Weather_Report.dto.LoginRequest;
import com.dice.Weather_Report.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController
{
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping
    public ResponseEntity<String> login(@Validated @RequestBody LoginRequest request)
    {
        System.err.println("user name ----------------->"+request.userName());

        System.out.println(request.password());
        Authentication authenticate = authenticationProvider
                .authenticate
                        (
                                new UsernamePasswordAuthenticationToken
                                        (
                                                request.userName(),

                                                request.password()
                                        )
                        );

        String token = jwtUtil.generateToken(request.userName());
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}
