package com.cheeus.config.auth.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.CookieGenerator;

import com.cheeus.config.auth.token.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7); // "Bearer " 다음의 토큰 부분만 추출

                if (jwtUtil.isExpired(token)) {
                    String username = jwtUtil.getUsername(token);
                    System.out.println(username);

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(username, null, null);

                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    // 토큰 재발급 로직
                    Date expirationDate = jwtUtil.getExpirationDateFromToken(token);
                    Date now = new Date();
                    long remainingTime = expirationDate.getTime() - now.getTime();

                    // 토큰 만료 30분 이내라면 재발급
                    if (remainingTime < 30 * 60 * 1000) {
                        String refreshedToken = jwtUtil.refreshToken(token);
                        if (refreshedToken != null) {
                            response.setHeader("Authorization", "Bearer " + refreshedToken);
                        }
                    }
                } else {
                	System.out.println("-----토큰 재발급-----");
                	System.out.println("토큰 재발급 : " + authorizationHeader);
                	System.out.println("토큰 재발급 : " + request.getParameter("email"));
                	String email = request.getParameter("email");
                	String refreshToken = jwtUtil.createJwt("google", email, "ROLE_USER", 60*60*60L);
                	System.out.println("refreshToken" + refreshToken);
                	response.setHeader("Authorization", refreshToken);

                    CookieGenerator cookieGenerator = new CookieGenerator();
                    cookieGenerator.setCookieName("Authorization");
                    cookieGenerator.addCookie(response, refreshToken);
                    System.out.println(response.getHeaders("Authorization"));

                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }
}