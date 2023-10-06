package com.example.socialmediabackenddemo.Common.jwt_services.Util;

import com.example.socialmediabackenddemo.Model.Business.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${serverSecretKey}")
    private String SECRET_KEY;
    @Value("${tokenValidity}")
    public int TOKEN_VALIDITY;

    public String getUsernameFromToken(String token ){
        return getClaimFromToken(token, Claims::getSubject);
    }
    public String getRoleFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get("role",String.class);
    }
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("Name",user.getName());
        claims.put("role",user.getRole().getRoleName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())//sub
                .setIssuedAt(new Date(System.currentTimeMillis()))//iat
                .setExpiration(new Date(System.currentTimeMillis() +  TOKEN_VALIDITY * 1000L))//exp
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
