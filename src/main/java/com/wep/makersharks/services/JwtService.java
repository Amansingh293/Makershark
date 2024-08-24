package com.wep.makersharks.services;

import com.wep.makersharks.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "b0JXWlo0czA1Y2xLZ2F3eHkwcG9yM2MxZGVyYXlzMWNpaTBuYWE1MHdpN2plZHJ0OWhmNXpoZ3M3dzA5cHJpd2FsaTY2bDJwZQ==";

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Transactional
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Transactional
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject); // Assuming the subject is the email
    }

    @Transactional
    public boolean isValid(String token, UserDetails user) {
        String username = extractEmail(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    @Transactional
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    @Transactional
    public String generateToken(User user) {

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3*24*60*60*1000)) // Corrected duration
                .signWith(getSigninKey())
                .compact();
    }





    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // Fetch from environment variable
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
