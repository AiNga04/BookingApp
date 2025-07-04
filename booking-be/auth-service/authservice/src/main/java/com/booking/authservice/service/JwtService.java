package com.booking.authservice.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

  @Value("${JWT_SECRET}")
  private String secret;

  private Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  public String generateToken(String username, String role) {
    return Jwts.builder()
        .setSubject(username)
        .claim("role", role)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public String refreshToken(String oldToken) {
    try {
      var claimsJws = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(oldToken);

      var claims = claimsJws.getBody();
      String username = claims.getSubject();
      String role = claims.get("role", String.class);

      return generateToken(username, role);
    } catch (ExpiredJwtException e) {
      throw new RuntimeException("Token is expired and cannot be refreshed");
    }
  }


  public String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public String extractRole(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .get("role", String.class);
  }
}
