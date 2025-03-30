package com.example.study_scape.Backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private int jwtExpirationInMs;

    // ✅ Get signing key
    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ✅ Generate JWT Token with studentId as subject
    public String generateToken(Authentication authentication) {
        CustomStudentDetails studentDetails = (CustomStudentDetails) authentication.getPrincipal();

        // ✅ Extract studentId from CustomStudentDetails
        String studentId = studentDetails.getUsername();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "STUDENT"); // Add custom claims if required

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(studentId) // ✅ Set studentId as subject
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Generate Token using Student ID (if required)
    public String generateTokenFromStudentId(String studentId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "STUDENT"); // Add claims if needed

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(studentId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extract Student ID from Token
    public String getStudentIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // ✅ Get Claims from Token
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // ✅ Get All Claims
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Check if Token is Expired
    public boolean isTokenExpired(String token) {
        final Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    // ✅ Validate Token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String studentId = getStudentIdFromToken(token);
        return (studentId.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // ✅ Validate Token without UserDetails (for basic validation)
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
