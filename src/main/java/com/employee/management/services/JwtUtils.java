package com.employee.management.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    @Value("${jwt.secret}")
    private String secretKey ;

    @Value("${jwt.validity}")
    private  long validity ;

    public String generateToken(String username , String email , String role){
        try{
            Map<String , Object> claims = new HashMap<>() ;
            claims.put("email" , email);
            claims.put("role" , role);

            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + validity))
                    .signWith(getKey()).compact();
        }catch (Exception e){
            logger.error("There's an error while generating token for user : {}" , username , e);
            throw new RuntimeException("Failed -- token generation" ,e) ;
        }
    }
//    private Key getKey(){
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        if (keyBytes.length < 32) {
//            throw new IllegalArgumentException("Key must be at least 256-bit (32 bytes)");
//        }
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token , UserDetails userDetails){
        try{
            final String username = extractUsername(token) ;
            return  (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

        }catch (Exception e){
            logger.warn("Invalid JWT token : {}" , e.getMessage());
            return false ;
        }
    }

}
