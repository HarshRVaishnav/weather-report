package com.dice.Weather_Report.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils
{
    @Value("${app.secret}")
    private String secretKey;

    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    // what
    // 1>  Generate token
    public String generateToken(String userName)
    {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuer("DuraTech")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // token is then SIGNED using the HS512 algorithm and the secret key.
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    //2>  read token
    public Claims getClaims(String jwtToken)
    {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                // parseClaimsJws decodes the token , verify the signature , issuer  , claim
                // claim is user info
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    // whose
    // 3>
    public String getUserName(String jwtToken)
    {
        return getClaims(jwtToken)
                .getSubject();
    }

    // when
    //4>
    public boolean isTokenValid(String jwtToken, String dbUser)
    {
        String userName = getUserName(jwtToken);

        return (userName.equals(dbUser)
                &&
                !isTokenExpired(jwtToken));
    }

    //then
    // 5>
    public Date getExpiry(String jwtTokern)
    {
        return getClaims(jwtTokern)
                .getExpiration();
    }

    //6>
    public boolean isTokenExpired(String jwtToken)
    {
        Date expiration = getClaims(jwtToken)
                .getExpiration();

        return expiration.before(new Date(System.currentTimeMillis()));
    }



}
