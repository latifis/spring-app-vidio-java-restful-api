package com.latif.vidiojavaspringboot.util;

import com.latif.vidiojavaspringboot.domain.dto.res.ResUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JWTGenerator {

    // Secret key used for signing and verifying JWT
    private static final String SECRET_KEY = "YOUR_SECRET_KEY_THAT_HAS_256_LONG";

    // Static instance of JWTGenerator
    private static final JWTGenerator instance = new JWTGenerator();

    // Method to create JWT
    public String createJwt(ResUserDto req) {
        // Signature algorithm used
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // Current time in milliseconds
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // Convert secret key into byte array
        byte[] apiKeySecretBytes = SECRET_KEY.getBytes();

        // Create SecretKeySpec from the secret key
        SecretKeySpec signInKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Create JWT builder
        JwtBuilder builder = Jwts.builder()
                .setSubject(req.getEmail())
                .claim("id", req.getId())
                .claim("username", req.getUsername())
                .claim("type", req.getType())
                .setIssuer("admin")
                .setAudience("user")
                .signWith(signInKey, signatureAlgorithm);

        // Set token expiration time (1 hour)
        long expMillis = nowMillis + 3600000L; // 1 hour expiration
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        // Generate encrypted JWT token
        return builder.compact();
    }

    // Method to decode JWT
    public Claims decodeJwt(String jwt) {
        // Decode JWT and return the body (claims) of the token
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(jwt).getBody();
    }
}
