package com.cheeus.admin.login.controller;

import com.cheeus.admin.login.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cheeus.config.auth.token.JWTUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import com.cheeus.config.auth.cookie.CookieUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminlogin")
@RequiredArgsConstructor
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminService;

    private final CookieUtil cookieUtil;

    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData,
    		HttpServletResponse response) {
        try {
            String id = loginData.get("id");
            String password = loginData.get("password");

            if(id.equals("admin1")&&password.equals("admin1")) {
                Map<String, Object> map = new HashMap<>();
                map.put("registrationId", password);
                map.put("email", null);
                map.put("id", id);
                
                String adminToken = jwtUtil.createJwt(map,"ROLE_ADMIN",86400000L);
//                String adminToken = jwtUtil.createJwt(map,"ROLE_ADMIN",10000L);
                
                cookieUtil.addCookie(response, "Authorization", adminToken, 0);
                
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("token", adminToken);
                
                return ResponseEntity.ok(responseBody);
            }

            return ResponseEntity.ok("실패");

        } catch (Exception e) {
        	return ResponseEntity.status(401).body("<<LogIn Error>> Invalid credentials");
        }
    }
    
    @GetMapping("/tokenCheck")
    public ResponseEntity<?> tokenCheck() {
    	
    	return ResponseEntity.ok("token check");
    }
}
