//package com.AccioJob.VoiceToDoListWithAi.Service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtParser;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//
//@Service //or @Componant also fine
//public class JWTService {
//
//    private final JwtParser jwtParser;
//    @Autowired
//    public  JWTService(JwtParser jwtParser){
//        this.jwtParser=jwtParser;
//    }
//
//
//
//    private static  final String SECRET_KEY="";
//    private Key getSigninKey(){
//        byte[] keyBytes=SECRET_KEY.getBytes(StandardCharsets.UTF_8);
//        return Keys.hmacShaKeyFor(keyBytes);
//
//    }
//    public boolean isValidToken(String token) {
//        Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJwt(token);
//        return true;
//
//
//    }
//
//    public Claims parseToken(String token){
//        try{
//            return jwtParser.parseClaimsJws(token).getBody();
//
//        }
//        catch (Exception e){
//            throw new RuntimeException("Invalid JWT token");
//        }
//    }
//
//    public Boolean validateToken(String token){
//        return  true;
//       // jwtParser.parseClaimsJws(token);
//    }
//    public String getEmailFromClaims(String token){
//        Claims claims=parseToken(token);
//        //i need email
//        String email=claims.get("email",String.class);
//        return email;
//    }
//
//    public String getPasswordFromClaims(String token){
//        Claims claims=parseToken(token);
//        //i need email
//        String password=claims.get("password",String.class);
//        return password;
//    }
//
//
//
//}
