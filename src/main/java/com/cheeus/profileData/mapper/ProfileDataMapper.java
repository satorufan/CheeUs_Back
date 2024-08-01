package com.cheeus.profileData.mapper;

import com.cheeus.profileData.dto.ProfileDataDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfileDataMapper {
    List<ProfileDataDto> findProfileData(String email);
}
