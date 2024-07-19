package com.cheeus.member.response;

import java.util.List;

import com.cheeus.member.domain.MemberProfile;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;

import lombok.Getter;

@Getter
public class ProfileWithImageResponse {
    private MemberProfile profile;
    private byte[] imageBlob;

    // Constructor
    public ProfileWithImageResponse(MemberProfile profile, byte[] imageBlob) {
        this.profile = profile;
        this.imageBlob = imageBlob;
    }

    // Getters and setters
    public MemberProfile getProfile() {
        return profile;
    }

    public void setProfile(MemberProfile profile) {
        this.profile = profile;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    
}