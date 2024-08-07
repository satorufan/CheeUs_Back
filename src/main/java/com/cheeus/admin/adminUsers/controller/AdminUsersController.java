package com.cheeus.admin.adminUsers.controller;

import com.cheeus.admin.adminUsers.dto.AdminUsersDto;
import com.cheeus.admin.adminUsers.service.AdminUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminUsersController {

    @Autowired
    private AdminUsersService service;

    @GetMapping("/AdminUser")
    public List<AdminUsersDto> getAllProfileData() {
        return service.findAll();
    }

    @GetMapping("/AdminUser/{email}")
    public List<AdminUsersDto> getOneProfileData(String email) {
        return service.findByEmail(email);
    }

    @PostMapping
    public void createProfileData(@RequestBody AdminUsersDto profileData) {
        service.save(profileData);
    }

    @PutMapping("/AdminUser/{email}")
    public void updateProfileData(@PathVariable("email") String email, @RequestBody AdminUsersDto profileData) {
        profileData.setEmail(email);
        service.save(profileData);
    }

    @DeleteMapping("/AdminUser/{email}")
    public void deleteProfileData(@PathVariable("email") String email) {
        service.delete(email);
    }
}
