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

    
    private String jwtSecret = "myKey123$$$$$@@@__))((&^%))%^&*\\#(@($*%))";

    
    private int jwtExpirationMs = 3000;

    public String getJwtFromHeader(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String generateTokenFromUsername(UserDetails userDetails) {

        String username = userDetails.getUsername();
        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(new Date()
            .getTime()+jwtExpirationMs))
            .signWith(key())
            .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts
                .parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

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
