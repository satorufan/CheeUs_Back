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

    public AdminLogin login(String id, String password) throws Exception {
        AdminLogin admin = adminMapper.findAdminById(id);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }

        return null;
    }
}
