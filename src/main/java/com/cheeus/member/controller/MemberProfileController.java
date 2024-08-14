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
import com.cheeus.member.domain.MemberScrap;
import com.cheeus.member.response.MyInsertedPostResponse;
import com.cheeus.member.response.ProfileWithImageResponse;
import com.cheeus.member.service.MemberProfileService;

import lombok.RequiredArgsConstructor;

/////// 기능 ///////
//1. 내프로필 불러오기(get)					<- 이 부분에서 로직 구현.
//2. 닉네임 중복 확인(get)		/checkNickname
//3. 프로필 정보 수정(post)	/edit
//4. 위치 및 매칭 동의(post)	/allow
//5. 좋아요 불러오기(get)		/like
//6. 좋아요 누르기(post)		/addLike		<- 이 부분에서 '좋아요 추가', '좋아요 취소' 모두 처리합니다

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class MemberProfileController {
	
	private final MemberProfileService profileService;
	private final ImageGetService imageGetService;
	
	// 내 프로필 불러오기
	@GetMapping
    public ResponseEntity<?> loadProfile(
    		@RequestParam("email") String email
    		) throws Exception {
		
        try {
            // Fetch profile information
            MemberProfile profile = profileService.findByEmail(email);
            

            // Fetch image blob
//            ArrayList<byte[]> imageBlob = imageGetService.getImg("profile/", email, profile.getPhoto());
            ArrayList<byte[]> imageBlob = imageGetService.getImg("profile", email, profile.getPhoto());
            // Fetch image type
            ArrayList<String> imageType = imageGetService.getType("profile", email, profile.getPhoto());
            
            // Fetch popularity
            ArrayList<MemberPopularity> popularity = profileService.loadPopularity(email);
            // Fetch scrap
            ArrayList<MemberScrap> scrap = profileService.findMyScrap(email);
            // Fetch scrap
            ArrayList<MyInsertedPostResponse> myPost = profileService.findMyPost(email);
            
            // Create a response object containing both image and profile
            ProfileWithImageResponse response = new ProfileWithImageResponse(profile, imageBlob, imageType, popularity, scrap, myPost);
            
            // Return response with HTTP status 200 OK
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            // Handle IOException appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to load profile and image: " + e.getMessage());
        }
    }
    // 타 유저 불러오기
    @GetMapping("/others")
    public ResponseEntity<?> loadOtherProfile(
    		@RequestParam("email") String encodedEmail,
    		@RequestParam("iv") String iv
    		) throws Exception {

    	String email = profileService.decrypt(encodedEmail, iv).replace("\"", "");
        try {
            // Fetch profile information
            MemberProfile profile = profileService.findByEmail(email);
            

//            // Fetch image blob
//            ArrayList<byte[]> imageBlob = imageGetService.getImg("profile/", email, profile.getPhoto());
//            // Fetch image type
//            ArrayList<String> imageType = imageGetService.getType("profile/", email, profile.getPhoto());
            // Fetch image blob
            ArrayList<byte[]> imageBlob = imageGetService.getImg("profile", email, profile.getPhoto());
            // Fetch image type
            ArrayList<String> imageType = imageGetService.getType("profile", email, profile.getPhoto());
            
            // Fetch popularity
            ArrayList<MemberPopularity> popularity = profileService.loadPopularity(email);
            // Fetch scrap
            ArrayList<MemberScrap> scrap = profileService.findMyScrap(email);
            // Fetch scrap
            ArrayList<MyInsertedPostResponse> myPost = profileService.findMyPost(email);
            
            // Create a response object containing both image and profile
            ProfileWithImageResponse response = new ProfileWithImageResponse(profile, imageBlob, imageType, popularity, scrap, myPost);
            
            // Return response with HTTP status 200 OK
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            // Handle IOException appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to load profile and image: " + e.getMessage());
        }
    }
	
	// 프로필 수정
	@PostMapping("/edit")
	public ResponseEntity<?> updateProfile(
			@RequestPart(value="memberProfileDetail") MemberProfile profile,
			@RequestParam(value="photos", required = false) List<MultipartFile> photos,
			@RequestParam(value="email") List<String> imageName
			) throws IOException {
		
		profileService.updateMember(profile, photos, imageName);
		
//		return loadProfile(profile.getEmail());
		return ResponseEntity.ok("수정완료");
	}
	
	//위치 매치 동의
	@PutMapping("/allow")
	public void updateProfileLocationMatch(
			@RequestParam(value="email") String email,
			@RequestParam(value="type") String type,
			@RequestParam(value="latitude", required=false) String latitude,
			@RequestParam(value="longitude", required=false) String longitude
			) {
		
		profileService.allowLocationMatching(email, type, latitude, longitude);
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
	
	
	
	// 찜한 글 불러오기
	@GetMapping("/scrap")
	public ResponseEntity<?> loadScrap(@RequestParam("email") String email) {
		
		return ResponseEntity.ok(profileService.findMyScrap(email));
	}
	
	
	// 찜하기
	@PostMapping("/addScrap")
	public ResponseEntity<?> addScrap(@RequestBody MemberScrap memberScrap) {
		
		return ResponseEntity.ok(profileService.addScrap(memberScrap));
	}

}
