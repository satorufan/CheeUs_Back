package com.cheeus.admin.login.service;

import com.cheeus.admin.login.mapper.AdminLoginMapper;
import com.cheeus.admin.login.model.AdminLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminLoginService {

    @Autowired
    private AdminLoginMapper adminMapper;

    private static final String SECRET_KEY = "your_secret_key";  // JWT 서명에 사용될 비밀키

    public String login(String id, String password) throws Exception {
        AdminLogin admin = adminMapper.findById(id);
        if (admin != null && admin.getPassword().equals(password)) {
            // JWT 토큰 생성
            return Jwts.builder()
                    .setSubject(id)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1일 만료 시간 설정
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        } else {
            throw new Exception("잘못된 자격 증명");
        }
    }
}
