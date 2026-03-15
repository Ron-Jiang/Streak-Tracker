// package com.streaktracker.streak_tracker.services;

// import java.security.Key;
// import java.util.Date;

// import javax.crypto.SecretKey;

// import org.springframework.stereotype.Service;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;
// import io.jsonwebtoken.security.SignatureAlgorithm;

// @Service
// public class JwtServicesImpl implements JwtServices {
//     private final Key signingKey = Keys.hmacShaKeyFor(com.streaktracker.streak_tracker.Keys.JWTKEY.getBytes());

//     public String generateJwtToken(String userId) {
//         String jws = Jwts.builder()
//             .subject(userId)
//             .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 604800000)).signWith(signingKey).compact();
//         return jws;
//     }

//     public String extractUserId(String jwtToken) {
//         return Jwts.parser()
//             .verifyWith((SecretKey) signingKey)
//             .build()
//             .parseSignedClaims(jwtToken)
//             .getPayload()
//             .getSubject();
//     }

//     public boolean isJwtTokenValid(String jwtToken) {
//         try {
//             Jwts.parser()
//                 .verifyWith((SecretKey) signingKey)
//                 .build()
//                 .parseSignedClaims(jwtToken);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }
// }
