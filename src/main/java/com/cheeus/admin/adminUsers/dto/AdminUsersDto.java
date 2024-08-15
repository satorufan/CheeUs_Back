package com.cheeus.admin.adminUsers.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminUsersDto {
    @JsonProperty("email")
    private String id;
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
    private boolean blacklist;
    private String intro;

}
