package com.cheeus.profileData.service;

import com.cheeus.profileData.dto.ProfileDataDto;
import com.cheeus.profileData.mapper.ProfileDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileDataServiceImpl implements ProfileDataService {

    @Autowired
    private ProfileDataMapper mapper;

    @Override
    public List<ProfileDataDto> findAll() {
        return mapper.findAll();
    }

    @Override
    public List<ProfileDataDto> findByEmail(String email) {
        return (List<ProfileDataDto>) mapper.findByEmail(email);
    }

    @Override
    public void save(ProfileDataDto profileData) {
        if (profileData.getEmail() == null) {
            mapper.insert(profileData);
        } else {
            mapper.update(profileData);
        }
    }

    @Override
    public void delete(String email) {
        mapper.delete(email);
    }
}
