package com.AccioJob.VoiceToDoListWithAi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.security.KeyFactory;
import java.security.PKCS12Attribute;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Configuration
public class JwtConfig {
    public static final String PUBLIC_KEY= "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAquEdNC/yDYqLTRskQSID\n" +
            "L8koeCDO3DVm44GBarbfgODViorwu1ov0mzBwGJNWcXHvVQHxsEx/QOs99zriz+X\n" +
            "Gzaztgeu5fkCIdsAH3EL02RMbn1MdnH14AFuJFoO/lldqUVnfW4sqPgUhOQ9TqTE\n" +
            "io2L4wxZNiRbVO0mDVA8wBVzBpMRzRZXvemIzoTER4CHCQMQAKiFrdmNeQgcSyXO\n" +
            "hGTlsuhPEJ1y5MXC3CcuTUPj4biKtNwROHZuaX4CfSemEueTpa1WjDXn60iMUBzf\n" +
            "/Tx/daS0WY6EwIepsBmHoxNTr8b2P/Xlvg7IvI1hp39MgSNGAYxTDSdH7cB5bto6\n" +
            "sQIDAQAB\n" +
            "-----END PUBLIC KEY-----";
    @Bean
    public JwtParser jwtParser(){
        return Jwts.parserBuilder().setSigningKey(getRSAPublicKey()).build();
//        return Jwts.setSigningKey(getRSAPrivateKey());
    }
    public RSAPublicKey getRSAPublicKey(){
        try{
            String publicKeyPEM= PUBLIC_KEY.replace("-----BEGIN PUBLIC KEY-----","")
                    .replace("-----END PUBLIC KEY-----","")
                    .replaceAll("\\s","");
            byte[] publicKeyBytes=Base64.getDecoder().decode(publicKeyPEM);
            X509EncodedKeySpec spec=new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory kf=KeyFactory.getInstance("RSA");
            return (RSAPublicKey) kf.generatePublic(spec);
        }
        catch (Exception e){
            throw  new RuntimeException("Failed to load RSA Public key",e);
        }
    }
    //Method to validate and parse token
    public Claims parseToken(String token) {
        try {
            return jwtParser().parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            //throw new RuntimeException("Invalid JWT token", e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid JWT token");
        }
    }
    //Helper method to get user role from token
    public String getUserRole(String token){
        Claims claims=parseToken(token);
        return claims.get("role", String.class);
    }
    public String getUserEmail(String token){
        Claims claims=parseToken(token);
        return claims.get("email",String.class);
    }
    public String getUserPassword(String token){
        Claims claims=parseToken(token);
        return claims.get("password",String.class);
    }
    public  boolean isTokenExpired(String token){
        Claims claims=parseToken(token);
        return claims.getExpiration().before(new Date());
    }
//    private RSAPrivateKey getRSAPrivateKey(){
//        try{
//            byte[] privateKeyBytes= Base64.getDecoder().decode(
//                PUBLIC_KEY.replace("-----BEGIN PUBLIC KEY-----","").replace("-----END PUBLIC KEY-----","")
//                        .replaceAll("\\s", "")
//            );
//            PKCS8EncodedKeySpec spec=new PKCS8EncodedKeySpec(privateKeyBytes);
//            KeyFactory kf=KeyFactory.getInstance("DSA");
//            return (RSAPrivateKey) kf.generatePrivate(spec);
//        }
//        catch (Exception exception){
//            throw new RuntimeException("Failed to load RSA key",exception);
//        }
//
//    }

}
