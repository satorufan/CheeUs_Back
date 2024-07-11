package com.cheeus.config.auth.token;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTUtil {
	
	@Value("${spring.jwt.secret}")
	private String key;

//    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {
//
//		byte[] byteSecretKey = Decoders.BASE64.decode(secret);
//        key = Keys.hmacShaKeyFor(byteSecretKey);
//    }


    public String getUsername(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("username", String.class);
    }

    public String getRole(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    public Boolean isExpired(String token) {
    	
    	System.out.println("JWTUtil - token expired check : "+ token);
    	
		try {

	        return Jwts
	        		.parserBuilder()
	        		.setSigningKey(key)
	        		.build()
	        		.parseClaimsJws(token)
	        		.getBody()
	        		.getExpiration()
	        		.before(new Date());
	        
		} catch (Exception e) {
			//System.out.println(e);
			return false;
		}
    }
    
    //토큰 생성
    public String createJwt(String registrationId, String username, String role, Long expiredMs) {

				Claims claims = Jwts.claims();
		claims.put("registrationId", registrationId);
        claims.put("email", username);
        claims.put("role", role);
        System.out.println("key : " + this);
        System.out.println(claims);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*5))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        
    }
    
    
    // 토큰 재발급
    public String refreshToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            String role = "ROLE_USER";
            return createJwt("google", username, role, 60*60*60L);
        } catch (Exception e) {
            return null;
        }
    }
    
    // 토큰 만료 시간 확인
    public Date getExpirationDateFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }
    
}
