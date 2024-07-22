package com.cheeus.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cheeus.firebase.ImageGetService;
import com.cheeus.member.domain.MemberPopularity;
import com.cheeus.member.domain.MemberProfile;
import com.cheeus.member.response.ProfileWithImageResponse;
import com.cheeus.member.service.MemberProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class MemberProfileController {
	
	private final MemberProfileService profileService;
	private final ImageGetService imageGetService;
	

    // 프로필 불러오기
    @GetMapping
    public ResponseEntity<?> loadProfile(@RequestParam("email") String email) {
        try {
            // Fetch profile information
            MemberProfile profile = profileService.findByEmail(email);

            // Fetch image blob
            ArrayList<byte[]> imageBlob = imageGetService.getImg("profile/", email, profile.getPhoto());
            
            // Fetch image type
            ArrayList<String> imageType = imageGetService.getType("profile/", email, profile.getPhoto());
            
            for(String type : imageType) {
            	System.out.println(type);
            }
            
            // Create a response object containing both image and profile
            ProfileWithImageResponse response = new ProfileWithImageResponse(profile, imageBlob, imageType);
            
            // Return response with HTTP status 200 OK
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            // Handle IOException appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to load profile and image: " + e.getMessage());
        }
    }
	
	// 닉네임 중복 확인
	@GetMapping("/checkNickname")
	public ResponseEntity<?> checkNickname(@RequestParam("nickname") String nickname) {
		System.out.println(nickname);
		return ResponseEntity.ok(profileService.existNickname(nickname));
	}
	
	// 프로필 수정
	@PostMapping("/edit")
	public ResponseEntity<?> updateProfile(
			@RequestPart(value="memberProfileDetail") MemberProfile profile,
			@RequestParam(value="photos") List<MultipartFile> photos,
			@RequestParam(value="email") List<String> imageName
			) throws IOException {
		System.out.println("edit");
		profileService.updateMember(profile, photos, imageName);
		
		return loadProfile(profile.getEmail());
	}
	
	
	
	// 좋아요 불러오기
	@GetMapping("/like")
	public ResponseEntity<?> loadPopularity(@RequestParam("email") String email) {
		
		return ResponseEntity.ok(profileService.findPopularity(email));
	}
	
	// 좋아요 누르기
	@PostMapping("/addLike")
	public ResponseEntity<?> addPopularity(@RequestBody MemberPopularity popularity) {
		
		profileService.addPopularity(popularity);
		return ResponseEntity.ok("좋아요 추가 및 제거");
	}

}
