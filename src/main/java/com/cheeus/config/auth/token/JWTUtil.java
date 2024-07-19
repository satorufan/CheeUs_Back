package com.cheeus.config.auth.token;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTUtil {
	
	@Value("${spring.jwt.secret}")
	private String key;


    public String getUsername(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("username", String.class);
    }

    public String getRole(String token) {

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    public Boolean isExpired(String token) {
    	
    	System.out.println("JWTUtil - token expired check : "+ token);
    	
		try {
			System.out.println(Jwts
	        		.parserBuilder()
	        		.setSigningKey(key)
	        		.build()
	        		.parseClaimsJws(token)
	        		.getBody()
	        		.getExpiration()
	        		.after(new Date()));
	        return Jwts
	        		.parserBuilder()
	        		.setSigningKey(key)
	        		.build()
	        		.parseClaimsJws(token)
	        		.getBody()
	        		.getExpiration()
	        		.after(new Date());
	        
		} catch (Exception e) {
			System.out.println("만료 되었습니다!");
			return false;
		}
    }
    
    //토큰 생성
    public String createJwt(Authentication authentication, String registrationId, 
    		String username, String role, Long expiredMs) {

				Claims claims = Jwts.claims();
		claims.put("registrationId", registrationId);
        claims.put("email", username);
        claims.put("role", role);
        System.out.println("key : " + this);
        System.out.println(authentication.getName());

        return Jwts.builder()
                .setClaims(claims)
        		.setSubject(authentication.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        
    }
    
    
    // 토큰 재발급
    public String refreshToken(String token) {
        try {
        	Authentication authentication = getAuthentication(token);
        	
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            String role = "ROLE_USER";
            return createJwt(authentication, "google", username, role, 60*60*60L);
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
    
    //인증된 토큰인지 확인하기 위해 토큰을 디코딩하는 작업.
    public Authentication getAuthentication(String token) {
    	System.out.println("JWTUtil - getAuthentication");
        Claims claims = parseClaims(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
		
        // 2. security의 User 객체 생성
        System.out.println(claims.getSubject());
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
    private List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        return Collections.singletonList(new SimpleGrantedAuthority(
                claims.get("role").toString()));
    }
    
    private Claims parseClaims(String token) {
        try {
            return Jwts
            		.parserBuilder()
            		.setSigningKey(key)
            		.build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (MalformedJwtException e) {
            throw e;
        } catch (SecurityException e) {
            throw e;
        }
    }
    
}
