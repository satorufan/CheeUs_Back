package com.cheeus.profileData.service;

import com.cheeus.profileData.dto.ProfileDataDto;

import java.util.List;

public interface ProfileDataService {
    List<ProfileDataDto> findProfileData(String email);
}