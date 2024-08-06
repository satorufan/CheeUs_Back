package com.cheeus.member.response;

import java.util.ArrayList;

import com.cheeus.member.domain.MemberPopularity;
import com.cheeus.member.domain.MemberProfile;
import com.cheeus.member.domain.MemberScrap;

public class ProfileWithImageResponse {
    private MemberProfile profile;
    private ArrayList<byte[]> imageBlob;
    private ArrayList<String> imageType;
    private ArrayList<MemberPopularity> popularity;
    private ArrayList<MemberScrap> scrap;
    private ArrayList<MyInsertedPostResponse> myPost;

    // Constructor
    public ProfileWithImageResponse(
    		MemberProfile profile, 
    		ArrayList<byte[]> imageBlob,
    		ArrayList<String> imageType,
    		ArrayList<MemberPopularity> popularity,
    		ArrayList<MemberScrap> scrap,
    		ArrayList<MyInsertedPostResponse> myPost) {
        this.profile = profile;
        this.imageBlob = imageBlob;
        this.imageType = imageType;
        this.popularity = popularity;
        this.scrap = scrap;
        this.myPost = myPost;
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
    
    public ArrayList<MemberPopularity> getPopularity() {
    	return popularity;
    }
    
    public void setPopularity(ArrayList<MemberPopularity> popularity) {
    	this.popularity = popularity;
    }
    
    public ArrayList<MemberScrap> getScrap() {
    	return scrap;
    }
    
    public void setScrap(ArrayList<MemberScrap> scrap) {
    	this.scrap = scrap;
    }

    public ArrayList<MyInsertedPostResponse> getMyPost() {
    	return myPost;
    }
    
    public void setMyPost(ArrayList<MyInsertedPostResponse> myPost) {
    	this.myPost = myPost;
    }
    
}