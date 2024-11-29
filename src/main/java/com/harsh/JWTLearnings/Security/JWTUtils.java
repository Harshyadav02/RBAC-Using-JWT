package com.harsh.JWTLearnings.Security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JWTUtils {


    private  String jwtSecret = "Mykwyfjdslflshlsf234567543ldfhsakljldlfhsflsdfdshfdsfl";


    private int jwtExpirationMs = 300000;  // token expire time in milisecond

    // method to get token from header
    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(14).trim(); // Remove 'Bearer ' prefix and any whitespace
        }
        return null;
    }


    // method that genrate token from username
    public String generateTokenFromUsername(UserDetails userDetails) {

        String username = userDetails.getUsername();
        String token = Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(new Date()
            .getTime()+jwtExpirationMs))
            .signWith(key())
            .compact();
        return token;
    }

    // method that retive username from token
    public String getUsernameFromJwtToken(String token) {
        return Jwts
                .parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // method to validate token
    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().verifyWith(key()).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());   
        }return false;
    }
    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
