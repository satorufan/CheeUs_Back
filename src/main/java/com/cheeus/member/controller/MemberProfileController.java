package com.cheeus.member.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cheeus.firebase.ImageGetService;
import com.cheeus.firebase.ImageUploadService;
import com.cheeus.member.domain.MemberPopularity;
import com.cheeus.member.domain.MemberProfile;
import com.cheeus.member.response.ProfileWithImageResponse;
import com.cheeus.member.service.MemberProfileService;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class MemberProfileController {
	
	private final MemberProfileService profileService;
	private final ImageUploadService imageUploadService;
	private final ImageGetService imageGetService;
	
//	// 나의 프로필 불러오기
//	@GetMapping
//	public ResponseEntity<?> loadProfile(@RequestParam("email") String email) throws IOException{
//		ResponseEntity.ok(imageGetService.getImg(email));
//		return ResponseEntity.ok(profileService.findByEmail(email));
//	}

    // Load profile and image blob
    @GetMapping
    public ResponseEntity<?> loadProfile(@RequestParam("email") String email) {
        try {
        	System.out.println(email);
            // Fetch image blob
            byte[] imageBlob = imageGetService.getImg(email);
            
            // Fetch profile information
            MemberProfile profile = profileService.findByEmail(email);
            
            // Create a response object containing both image and profile
            ProfileWithImageResponse response = new ProfileWithImageResponse(profile, imageBlob);
            System.out.println(response.getImageBlob());
            System.out.println(response.getProfile());
            
            // Return response with HTTP status 200 OK
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            // Handle IOException appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to load profile and image: " + e.getMessage());
        }
    }
	
	// 사진 받기
	@PostMapping("/receivePhotos")
	public void receivePhotos(
			@RequestParam(value="photos") List<MultipartFile> photos,
			@RequestParam(value="email") List<String> imageName) throws IOException {
		
		for(MultipartFile photo : photos) {
			File tmp = imageUploadService.convertToFile( photo , "test" );
			String completeMsg = imageUploadService.uploadFile(tmp, "profile/" + imageName.get(photos.indexOf(photo)) );
			System.out.println(completeMsg);
		};
	}
	
	// 닉네임 중복 확인
	@PostMapping("/checkNickname")
	public ResponseEntity<?> checkNickname(@RequestParam("nickname") String nickname) {
		
		return ResponseEntity.ok(profileService.existNickname(nickname));
	}
	
	// 프로필 수정
	@PutMapping("/edit")
	public ResponseEntity<?> updateProfile(@RequestBody MemberProfile membeProfile) {
		
		return ResponseEntity.ok(profileService.updateMember(membeProfile));
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
