//package com.example.Swipe.Admin.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.*;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//    private static final String SECRETE_KEY = "2F423F4528482B4D6251655368566D597133743677397A24432646294A404E63";
//    public String extractUsername(String token) {
//        return extractClaim(token,Claims::getSubject);
//    }
//    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    public String generateToken(UserDetails userDetails) {
//        return generateToken(new HashMap<>(), userDetails);
//    }
//
////    public String generateToken(
////            Map<String, Object> extraClaims,
////            UserDetails userDetails
////    ) {
////        return buildToken(extraClaims, userDetails, 86400000 );
////    }
//    public String generateToken(
//            Map<String, Object> extraClaims,
//            UserDetails userDetails
//    ) {
//        return Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
//                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//
////    public String generateRefreshToken(
////            UserDetails userDetails
////    ) {
////        return buildToken(new HashMap<>(), userDetails, 604800000 );
////    }
//
////    private String buildToken(
////            Map<String, Object> extraClaims,
////            UserDetails userDetails,
////            long expiration
////    ) {
////        return Jwts
////                .builder()
////                .setClaims(extraClaims)
////                .setSubject(userDetails.getUsername())
////                .setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis() + expiration))
////                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
////                .compact();
////    }
////    public String generateToken(UserDetails userDetails){
////        return generateToken(new HashMap<>(),userDetails);
////
////    }
////    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails){
////        return Jwts.builder()
////                .setClaims(extractClaims)
////                .setSubject(userDetails.getUsername())
////                .setIssuedAt(new Date(System.currentTimeMillis()))
////                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
////                .signWith(getSignInKey(), SignatureAlgorithm.ES256).compact();
////    }
//    public boolean isTokenValid(String token, UserDetails userDetails){
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()))&&!isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token,Claims::getExpiration);
//    }
//
//    private Claims extractAllClaims(String token){
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//
//    public Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRETE_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
