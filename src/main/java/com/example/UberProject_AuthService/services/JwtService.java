package com.example.UberProject_AuthService.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String SCERET;

    // this function generates JWT token

    public String generateToken(Map<String, Object> payload, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000L);

        SecretKey key = Keys.hmacShaKeyFor(SCERET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .subject(subject)
                .signWith(key)
                .compact();

    }

}
