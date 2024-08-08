package com.cheeus.admin.login.controller;

import com.cheeus.admin.login.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cheeus.config.auth.token.JWTUtil;
import com.cheeus.config.auth.cookie.CookieUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminlogin")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminService;

    @Autowired
    private CookieUtil cookieUtil;

    private JWTUtil jwtUtil;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String id = loginData.get("id");
            String password = loginData.get("password");

            System.out.println("받아온 id : " + id);
            System.out.println("받아온 password : " + password);

            if(id=="admin1"&&password=="admin1") {
                Map<String, Object> map = new HashMap<>();
                map.put("registrationId", password);
                map.put("email", id);
                String adminToken = jwtUtil.createJwt(map,"ROLE_ADMIN",86400000L);

                cookieUtil.addCookie(null,"adminAuthrization", adminToken, 86400);
            }

            String token = adminService.login(id, password);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("<<LogIn Error>> Invalid credentials");
        }
    }
}
