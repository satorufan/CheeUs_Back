package com.cheeus.config.auth.filter;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cheeus.config.auth.CustomOAuth2User;
import com.cheeus.config.auth.TokenService;
import com.cheeus.config.auth.domain.OAuth2Attribute;
import com.cheeus.config.auth.token.JWTUtil;
import com.cheeus.member.domain.Member;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
//public class JwtAuthFilter extends GenericFilterBean {
//    private final TokenService tokenService;
//    private final JWTUtil jwtUtil;
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        
//    	System.out.println("doFilter - Request : " + ((HttpServletRequest)request));
//    	
//    	String token = ((HttpServletRequest)request).getHeader("Auth");
//    	System.out.println("doFilter - token : " + token);
//
//        if (token != null && tokenService.verifyToken(token)) {
//            String email = tokenService.getUid(token);
//
//            Member member = Member.builder()
//                    .email(email)
//                    .build();
//
//            Authentication auth = getAuthentication(member);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    public Authentication getAuthentication(Member member) {
//    	System.out.println("getAuthentication - Member : " + member);
//        return new UsernamePasswordAuthenticationToken(member, "",
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
//    }
//}

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
    	System.out.println("-----JWT Filter-----");
        //cookie들을 불러온 뒤 Authorization Key에 담긴 쿠키를 찾음
        String authorization = null;
        Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
	        for (Cookie cookie : cookies) {
	
	            if (cookie.getName().equals("Authorization")) {
	
	                authorization = cookie.getValue();
	                System.out.println(authorization);
	            }
	        }
        }

        //Authorization 헤더 검증
        if (authorization == null) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        //토큰
        String token = authorization;

        //토큰 소멸 시간 검증
        if (!jwtUtil.isExpired(token)) {
        	
            System.out.println("Token 만료");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        //userDTO를 생성하여 값 set
        Member member = new Member();
        member.setEmail(username);
        //member.setRole(role);

        //UserDetails에 회원 정보 객체 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(member);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);
        
        System.out.println("-----JWT Filter END-----");

        filterChain.doFilter(request, response);
    }
}