package com.cheeus.profileData.controller;

import com.cheeus.profileData.dto.ProfileDataDto;
import com.cheeus.profileData.service.ProfileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/profileData")
public class ProfileDataController {

    @Autowired
    private ProfileDataService profileDataService;

    @GetMapping("/{email}")
    public List<ProfileDataDto> getProfileData(@PathVariable("email") String email) {
        return profileDataService.findProfileData(email);
    }
}
