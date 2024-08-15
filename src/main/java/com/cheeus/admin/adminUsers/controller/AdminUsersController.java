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

    @GetMapping("/AdminUser/{id}")
    public List<AdminUsersDto> getOneProfileData(String id) {
        return service.findByEmail(id);
    }

    @PostMapping
    public void createProfileData(@RequestBody AdminUsersDto profileData) {
        service.save(profileData);
    }

    @PutMapping("/AdminUser/{id}")
    public void updateProfileData(@PathVariable("id") String id, @RequestBody AdminUsersDto profileData) {
        profileData.setId(id);
        service.save(profileData);
    }

    @DeleteMapping("/AdminUser/{id}")
    public void deleteProfileData(@PathVariable("id") String id) {
        service.delete(id);
    }
}
