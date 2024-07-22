package com.cheeus.member.response;

import java.util.ArrayList;

import com.cheeus.member.domain.MemberProfile;

import lombok.Getter;

@Getter
public class ProfileWithImageResponse {
    private MemberProfile profile;
    private ArrayList<byte[]> imageBlob;
    private ArrayList<String> imageType;

    // Constructor
    public ProfileWithImageResponse(
    		MemberProfile profile, 
    		ArrayList<byte[]> imageBlob,
    		ArrayList<String> imageType) {
        this.profile = profile;
        this.imageBlob = imageBlob;
        this.imageType = imageType;
    }

    // Getters and setters
    public MemberProfile getProfile() {
        return profile;
    }

    public void setProfile(MemberProfile profile) {
        this.profile = profile;
    }

    public ArrayList<byte[]> getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(ArrayList<byte[]> imageBlob) {
        this.imageBlob = imageBlob;
    }
    
    public ArrayList<String> getImageType() {
        return imageType;
    }

    public void setImageType(ArrayList<String> imageType) {
        this.imageType = imageType;
    }

    
}