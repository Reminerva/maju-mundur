package com.majumundur.majumundur.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtTokenProvider {

    private String SECRET_KEY = "abcd";
    private Long EXPIRATION_TIME = 86400000L;

    public String getUsernameFromToken(String token){
        DecodedJWT decodedJWT = getDecodedJWT(token);
        return decodedJWT.getSubject();
    }

    public Boolean validateToken(String token){
        try {
            DecodedJWT decodedJWT = getDecodedJWT(token);
            return !decodedJWT.getExpiresAt().before((new Date()));
        } catch (Exception e) {
            return false;
        }
    }

    public String generateToken(String username, String role) {
        String token = JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY));
        return token;
    }

    public String getRoleFromToken(String token) {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        String role = decodedJWT.getClaim("role").asString();

        if (role.startsWith("[")) {
            role = role.substring(1, role.length()-1);
        }
        
        return role;
    }

    public Long getExpirationTime(String token){
        DecodedJWT decodedJWT = getDecodedJWT(token);
        Date expirationDate = decodedJWT.getExpiresAt();
        return expirationDate.getTime() - System.currentTimeMillis();
    }

    private DecodedJWT getDecodedJWT(String token) {
        
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET_KEY))
            .build()
            .verify(token);
        return decodedJWT;
    }

}
