package com.cheeus.profileData.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileDataDto {
    private String email;
    private String name;
    private String nickname;
    private int photo;
    private String tel;
    private String birth;
    private int gender;
    private String tags;
    private int matchOk;
    private int locationOk;
    private String latitude;
    private String longitude;
    private String location;
    private String intro;

}
