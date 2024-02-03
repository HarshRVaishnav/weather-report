package com.dice.Weather_Report.filter;

import com.dice.Weather_Report.service.impl.CustomUserDetailService;
import com.dice.Weather_Report.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component     // this class for custom filter
public class JwtFilter  extends OncePerRequestFilter            
{

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtils jwtUtils;



    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.equals("/register/user" )  ||   path.equals("/login" ) ;
    }

    @Override protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException
    {

        // I have Id card let me go in
         //step 1 ; EXTRACTING token from request header
        String token = request.getHeader("Authorization"); 

        if(token != null)
        {
            String userName = jwtUtils.getUserName(token);

            //step 2 : check if user not authenticated
            if(userName != null &&  SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UserDetails userDetails =
                        customUserDetailService.loadUserByUsername(userName);

                boolean tokenValid = jwtUtils.isTokenValid(token, userDetails.getUsername());


                // it's like creating NEW GATE PASS even though you have id card
                //step 3 : CREATING AUTHENTICATION object
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userName ,
                                userDetails.getPassword()     ,
                                        userDetails.getAuthorities()
                        );

                // step 4: setting DETAILS of authentication object
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                //step 5 : set authentication in security CONTEXT
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken );

            }
        }
         //chain continues
         filterChain.doFilter(request , response) ;
    }           
}
