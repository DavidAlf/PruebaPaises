package com.prueb.tecnica.paises.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String extratUserName(String token) {
        return extratClaims(token, Claims::getSubject);
    }

    public Date extratExpiration(String token) {
        return extratClaims(token, Claims::getExpiration);
    }

    public <T> T extratClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extratAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public Claims extratAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extratExpiration(token).before(new Date());
    }

    public String generateToken(String userName, String role) {
        Map<String, Object> claims = new HashMap<>();
        log.info("Opteniendo rol");
        claims.put("role", role);
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean valiateToken(String token, UserDetails userDateils) {
        final String userName = extratUserName(token);

        return (userName.equals(userDateils.getUsername()) && !isTokenExpired(token));
    }
}