//package com.booking.authservice.util;
//
//import java.util.Date;
//import org.springframework.stereotype.Component;
//import io.jsonwebtoken.*;
//import lombok.extern.slf4j.Slf4j;
//import me.loda.springsecurityhibernatejwt.user.CustomUserDetails;
//
//@Component
//@Slf4j
//public class JwtTokenProvider {
//  private final String JWT_SECRET = "lodaaaaaa";
//
//  private final long JWT_EXPIRATION = 604800000L;
//
//  public String generateToken(CustomUserDetails userDetails) {
//    Date now = new Date();
//    Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
//    // Tạo chuỗi json web token từ id của user.
//    return Jwts.builder()
//        .setSubject(Long.toString(userDetails.getUser().getId()))
//        .setIssuedAt(now)
//        .setExpiration(expiryDate)
//        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
//        .compact();
//  }
//
//  // Lấy thông tin user từ jwt
//  public Long getUserIdFromJWT(String token) {
//    Claims claims = Jwts.parser()
//        .setSigningKey(JWT_SECRET)
//        .parseClaimsJws(token)
//        .getBody();
//
//    return Long.parseLong(claims.getSubject());
//  }
//
//  public boolean validateToken(String authToken) {
//    try {
//      Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
//      return true;
//    } catch (MalformedJwtException ex) {
//      log.error("Invalid JWT token");
//    } catch (ExpiredJwtException ex) {
//      log.error("Expired JWT token");
//    } catch (UnsupportedJwtException ex) {
//      log.error("Unsupported JWT token");
//    } catch (IllegalArgumentException ex) {
//      log.error("JWT claims string is empty.");
//    }
//    return false;
//  }
//}