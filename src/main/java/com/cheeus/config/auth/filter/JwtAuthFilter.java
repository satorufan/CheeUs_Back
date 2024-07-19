package com.cheeus.config.auth.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.cheeus.config.auth.cookie.CookieUtil;
import com.cheeus.config.auth.token.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	System.out.println("-----JWT Filter-----");
    	
    	try {
    		//Authorization 쿠키를 불러온다.
    		Cookie cookie = WebUtils.getCookie(request, "Authorization");
    		//쿠키의 Value를 가져온다.
    		if (cookie != null) {
	    		String authorizationHeader = cookie.getValue();
	
	            if (authorizationHeader != null) {
	                String token = authorizationHeader;//.substring(7); // "Bearer " 다음의 토큰 부분만 추출
	                System.out.println("token : " + token);
	                System.out.println("토큰이 유효한가? : " + jwtUtil.isExpired(token));
	
	                if (jwtUtil.isExpired(token)) {
	                	
	                	setAuthentication(token);
	
	                    // 토큰 재발급 로직
	                    Date expirationDate = jwtUtil.getExpirationDateFromToken(token);
	                    Date now = new Date();
	                    long remainingTime = expirationDate.getTime() - now.getTime();
	
	                    // 토큰 만료 30분 이내라면 재발급
	                    if (remainingTime < 30 * 60 * 1000) {
	                        String refreshedToken = jwtUtil.refreshToken(token);
	                        setAuthentication(refreshedToken);
	                        CookieUtil.addCookie(response, "Authorization", refreshedToken, 60*60*24);
	                    }

	                }
	            }
    		}
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }
    
    private void setAuthentication(String accessToken) {
    	System.out.println(accessToken);
        Authentication authentication = jwtUtil.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    
}