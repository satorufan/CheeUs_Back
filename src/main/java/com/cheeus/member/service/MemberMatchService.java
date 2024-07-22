package com.cheeus.member.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cheeus.firebase.ImageGetService;
import com.cheeus.member.domain.MemberProfile;
import com.cheeus.member.repository.MemberMatchDao;
import com.cheeus.member.response.ProfileWithImageResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberMatchService {
	
	private final MemberMatchDao memberMatchDao;
	private final ImageGetService imageGetService;

	public List<ProfileWithImageResponse> findAll() {
		// response 초기화
		List<ProfileWithImageResponse> responses = new ArrayList<ProfileWithImageResponse> ();
		// 타 멤버 리스트 불러오기
		List<MemberProfile> profiles = memberMatchDao.findAll();
		
		try {
			// 하나 하나 정보랑 사진 넣기
			for (MemberProfile profile : profiles) {

	            // Fetch image blob
	            ArrayList<byte[]> imageBlob = imageGetService.getImg("profile/", profile.getEmail(), profile.getPhoto());
	            
	            // Fetch image type
	            ArrayList<String> imageType = imageGetService.getType("profile/", profile.getEmail(), profile.getPhoto());
	            
	            // Create a response object containing both image and profile
	            ProfileWithImageResponse response = new ProfileWithImageResponse(profile, imageBlob, imageType);
	            
				responses.add(response);
			}
            
            // Return response with HTTP status 200 OK
			return responses;
        } catch (IOException e) {
            // Handle IOException appropriately
            return null;
        }
		
	}
}
