package com.cheeus.profileData.service;

import com.cheeus.profileData.dto.ProfileDataDto;
import com.cheeus.profileData.mapper.ProfileDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileDataServiceImpl implements ProfileDataService {

    @Autowired
    private ProfileDataMapper profileDataMapper;

    @Override
    public List<ProfileDataDto> findProfileData(String email) {
        return profileDataMapper.findProfileData(email);
    }
}
