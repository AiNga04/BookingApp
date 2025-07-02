package com.leetjourney.apigateway.config;

import com.leetjourney.apigateway.util.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class SecurityConfig {

  // @Value("${keycloak.auth.jwk-set-uri}")
  // private String jwkSetUri;
  //
  // @Value("${security.excluded.urls}")
  // private String[] excludedUrls;
  //
  // @Bean
  // public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
  // throws Exception {
  // return httpSecurity.authorizeHttpRequests(authorizeRequests ->
  // authorizeRequests
  // .requestMatchers(excludedUrls)
  // .permitAll()
  // .anyRequest().authenticated())
  // .oauth2ResourceServer(oauth ->
  // oauth.jwt(Customizer.withDefaults()))
  // .build();
  // }

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            // Hotel & Room - GET: tất cả role
            .requestMatchers(HttpMethod.GET, "/api/v1/hotels/**", "/api/v1/rooms/**")
            .hasAnyRole("ADMIN", "MANAGER", "STAFF", "GUEST")
            // Hotel & Room - POST, PUT, DELETE: chỉ ADMIN, MANAGER
            .requestMatchers(HttpMethod.POST, "/api/v1/hotels/**", "/api/v1/rooms/**")
            .hasAnyRole("ADMIN", "MANAGER")
            .requestMatchers(HttpMethod.PUT, "/api/v1/hotels/**", "/api/v1/rooms/**")
            .hasAnyRole("ADMIN", "MANAGER")
            .requestMatchers(HttpMethod.DELETE, "/api/v1/hotels/**", "/api/v1/rooms/**")
            .hasAnyRole("ADMIN", "MANAGER")
            // User - chỉ ADMIN, MANAGER
            .requestMatchers("/api/v1/user/**").hasAnyRole("ADMIN", "MANAGER")
            // Auth - login/register: tất cả role (permitAll)
            .requestMatchers(HttpMethod.POST, "/api/v1/auth/login", "/api/v1/auth/register",
                "/api/v1/user/validate-credentials")
            .permitAll()
            // Các request khác: cần xác thực
            .anyRequest().authenticated())
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
    org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.addAllowedOriginPattern("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");

    org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  // @Bean
  // public JwtDecoder jwtDecoder() {
  // return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
  // }

  public static class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
      this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {
      String header = request.getHeader("Authorization");
      if (header != null && header.startsWith("Bearer ")) {
        String token = header.substring(7);
        if (jwtTokenProvider.validateToken(token)) {
          String username = jwtTokenProvider.getUsernameFromJWT(token);
          String role = jwtTokenProvider.getRoleFromJWT(token);

          System.out.println("[DEBUG] Username from token: " + username);
          System.out.println("[DEBUG] Role from token: " + role);

          List<SimpleGrantedAuthority> authorities;
          if (role != null) {
            authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
          } else {
            authorities = List.of();
          }

          System.out.println("[DEBUG] Authorities: " + authorities);

          UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
              username,
              null,
              authorities
          );

          auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(auth);

          filterChain.doFilter(request, response);
          return;
        } else {
          response.setStatus(HttpServletResponse.SC_NOT_FOUND);
          response.setContentType("application/json");
          response.getWriter().write("""
              {
                "success": false,
                "message": "Invalid token"
              }
              """);
          return;
        }
      }

      filterChain.doFilter(request, response);
    }
  }
}