package com.cheeus.config.auth.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.cheeus.config.auth.CustomOAuth2User;
import com.cheeus.config.auth.TokenService;
import com.cheeus.config.auth.domain.OAuth2Attribute;
import com.cheeus.config.auth.domain.Token;
import com.cheeus.config.auth.token.JWTUtil;
import com.cheeus.member.domain.Member;
import com.cheeus.member.repository.MemberDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    private final TokenService tokenService;
//    private final MemberDao dao;
//    //private final ObjectMapper objectMapper;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//            throws IOException, ServletException {
//        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
//        Member member = dao.findByEmail(oAuth2User.getName());
//
////        System.out.println("Principal에서 꺼낸 OAuth2User : " + oAuth2User);
//        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
//        // 최초 로그인이라면 회원가입 처리를 한다.
//        String targetUrl;
//        log.info("토큰 발행 시작");
//
//        Token token = tokenService.generateToken(member.getEmail(), "USER");
//        
////        System.out.println("Success : " + token);
//        log.info("{}", token);
//        log.info("request : {} / response : {}", request, response);
////        targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000")
////                .queryParam("token", token)
////                .build().toUriString();
//        targetUrl = "http://localhost:3000";
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);
//    }
//}

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    public OAuth2SuccessHandler(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    	System.out.println("Success Handler - onAuthenticationSuccess");
        //OAuth2User
    	CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

    	System.out.println(customUserDetails);
    	
        String username = customUserDetails.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(username, role, 60*60*60L);
        System.out.println("Success Handler - onAuthenticationSuccess - token : " + token);

        response.addCookie(createCookie("Authorization", token));
        response.sendRedirect("http://localhost:3000/");
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}