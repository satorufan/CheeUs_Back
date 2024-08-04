package com.cheeus.profileData.service;

import com.cheeus.profileData.dto.ProfileDataDto;

import java.util.List;

public interface ProfileDataService {
    List<ProfileDataDto> findAll();

    ProfileDataDto findById(String email);

    void save(ProfileDataDto profileData);

    void delete(String email);
}
