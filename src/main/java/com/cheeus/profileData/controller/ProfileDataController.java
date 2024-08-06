package com.cheeus.profileData.controller;

import com.cheeus.profileData.dto.ProfileDataDto;
import com.cheeus.profileData.service.ProfileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileDataController {

    @Autowired
    private ProfileDataService service;

    @GetMapping("/UserData")
    public List<ProfileDataDto> getAllProfileData() {
        return service.findAll();
    }

    @GetMapping("/UserData/{email}")
    public List<ProfileDataDto> getOneProfileData(String email) {
        return service.findByEmail(email);
    }

    @PostMapping
    public void createProfileData(@RequestBody ProfileDataDto profileData) {
        service.save(profileData);
    }

    @PutMapping("/UserData/{email}")
    public void updateProfileData(@PathVariable("email") String email, @RequestBody ProfileDataDto profileData) {
        profileData.setEmail(email);
        service.save(profileData);
    }

    @DeleteMapping("/{email}")
    public void deleteProfileData(@PathVariable("email") String email) {
        service.delete(email);
    }
}
