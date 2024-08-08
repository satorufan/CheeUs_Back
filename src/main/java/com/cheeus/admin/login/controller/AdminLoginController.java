package com.cheeus.admin.login.controller;

import com.cheeus.admin.login.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminlogin")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String id = loginData.get("id");
            String password = loginData.get("password");
            String token = adminService.login(id, password);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
