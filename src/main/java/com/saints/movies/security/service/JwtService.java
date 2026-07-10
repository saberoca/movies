package com.saints.movies.security.service;

import com.saints.movies.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-ms}")
    private long expirationMs;

    // ─── Generación ─────────────────────────────────────────────

    public String generateToken(UserDetailsImpl userDetails, String platform) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("userId", userDetails.getId())
                .claim("role", userDetails.getRole().name())
                .claim("platform", platform)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSecretKey())
                .compact();
    }

    // ─── Validación ─────────────────────────────────────────────

    public boolean isTokenValid(String token, String platform) {
        try {
            Claims claims = extractAllClaims(token);
            String tokenPlatform = claims.get("platform", String.class);
            return !isTokenExpired(claims) && platform.equalsIgnoreCase(tokenPlatform);
        } catch (Exception e) {
            return false;
        }
    }

    // ─── Extracción ─────────────────────────────────────────────

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractPlatform(String token) {
        return extractAllClaims(token).get("platform", String.class);
    }

    // ─── Métodos privados ────────────────────────────────────────

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}