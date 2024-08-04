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

    @GetMapping
    public List<ProfileDataDto> getAllProfileData() {
        return service.findAll();
    }

    @PostMapping
    public void createProfileData(@RequestBody ProfileDataDto profileData) {
        service.save(profileData);
    }

    @PutMapping("/{email}")
    public void updateProfileData(@PathVariable("email") String email, @RequestBody ProfileDataDto profileData) {
        profileData.setEmail(email);
        service.save(profileData);
    }

    @DeleteMapping("/{email}")
    public void deleteProfileData(@PathVariable("email") String email) {
        service.delete(email);
    }
}
