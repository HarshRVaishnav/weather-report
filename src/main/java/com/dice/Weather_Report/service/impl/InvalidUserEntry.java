package com.dice.Weather_Report.service.impl;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    @Component
    public class InvalidUserEntry implements AuthenticationEntryPoint
    {
        //when
        //  to handle the unauthorized access to protected resource
        @Override public void commence(
                HttpServletRequest request,   // this is the unauthorized request
                HttpServletResponse response,
                AuthenticationException authException     // exception thrown
        ) throws IOException, ServletException
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED
                    , "Unauthorized access to resource");          // 401
        }
    }
