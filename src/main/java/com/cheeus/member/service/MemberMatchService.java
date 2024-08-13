package com.cheeus.member.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cheeus.firebase.ImageGetService;
import com.cheeus.member.domain.MemberMatch;
import com.cheeus.member.domain.MemberPopularity;
import com.cheeus.member.domain.MemberProfile;
import com.cheeus.member.exception.MemberException;
import com.cheeus.member.repository.MemberMatchDao;
import com.cheeus.member.repository.MemberProfileDao;
import com.cheeus.member.request.MatchFindRequest;
import com.cheeus.member.response.LoadPersonalChattingInfo;
import com.cheeus.member.response.ProfileWithImageResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/////// 기능 ///////
//1. 매칭을 위한 타 유저 프로필 로드 (파이어베이스 + MySQL)
//2. 매칭 로직
//3. 채팅방에서 표시할 유저 정보 불러오기 (파이어베이스 사진 1장 + MySQL 닉네임, 이메일)

@Service
@RequiredArgsConstructor
public class MemberMatchService {
	
	private final MemberProfileDao memberProfileDao;
	private final MemberMatchDao memberMatchDao;
	private final ImageGetService imageGetService;

	public List<ProfileWithImageResponse> findAll(String email) {
		// response 초기화
		List<ProfileWithImageResponse> responses = new ArrayList<ProfileWithImageResponse> ();
		// 타 멤버 리스트 불러오기
		List<MemberProfile> profiles = memberMatchDao.findAll(email);
		// 내가 매치 선택한 리스트 불러오기
		ArrayList<String> myMatches = memberMatchDao.findMyMatch(email);
		
		try {
			// 하나 하나 정보랑 사진 넣기
			for (MemberProfile profile : profiles) {
				System.out.println(matchState(email, profile.getEmail()));
				
				if ((myMatches.indexOf(profile.getEmail()) == -1)
						&&
						(matchState(email, profile.getEmail()) < 2)) {
	
//		            // Fetch image blob
//		            ArrayList<byte[]> imageBlob = imageGetService.getImg("profile/", profile.getEmail(), profile.getPhoto());
//		            
//		            // Fetch image type
//		            ArrayList<String> imageType = imageGetService.getType("profile/", profile.getEmail(), profile.getPhoto());
					
					// Fetch image blob
		            ArrayList<byte[]> imageBlob = imageGetService.getImg("profile", profile.getEmail(), profile.getPhoto());
		            
		            // Fetch image type
		            ArrayList<String> imageType = imageGetService.getType("profile", profile.getEmail(), profile.getPhoto());
		            
		            // Fetch popularity
		            ArrayList<MemberPopularity> popularity = memberProfileDao.findPopularity(profile.getEmail());
		            
		            // Create a response object containing both image and profile
		            ProfileWithImageResponse response = new ProfileWithImageResponse(profile, imageBlob, imageType, popularity, null, null);
		            
					responses.add(response);
					
				}
			}
            
            // Return response with HTTP status 200 OK
			return responses;
        } catch (IOException e) {
            // Handle IOException appropriately
            return null;
        }
	}
	
	
	// 매치 상태 확인
	public int matchState (String member1, String member2) {

		int state1 = 0;
		int state2 = 0;
		
		int checkMatch1 = memberMatchDao.findMatchRooms(new MatchFindRequest(member1, member2));
		int checkMatch2 = memberMatchDao.findMatchRooms(new MatchFindRequest(member2, member1));
		
		if (checkMatch1 == 0 && checkMatch2 == 0) {
			return 0;
		} else if (checkMatch1 > 0) {

			state1 = memberMatchDao.loadMatchState(new MatchFindRequest(member1, member2));
		} else {
			
			state2 = memberMatchDao.loadMatchState(new MatchFindRequest(member2, member1));
		}
		
		return state1 > state2 ? state1 : state2;
	}
	
	
	// 매칭 알고리즘
	public MemberMatch match (String member1, String member2, String type) {
		
		int matchState = 0;
		
		// 매치 내역이 존재하는지 확인 / match랑 상관없음.
		int checkMatch1 = memberMatchDao.findMatchRooms(new MatchFindRequest(member1, member2));
		int checkMatch2 = memberMatchDao.findMatchRooms(new MatchFindRequest(member2, member1));
		
						
		if (checkMatch1 == 0 && checkMatch2 == 0) {	// 매치내역이 없다면, 매치 내역을 만들기.
			
			matchState = type.equals("right") ? 1 : 3;
			memberMatchDao.createMatchRooms(new MemberMatch(0, member1, member2, matchState));
			return null;
			
		} else {	// 매치내역 있을 경우
			if (checkMatch1 > 0) {	// 있으면 기존 매치 + 1, 2면 매칭성공 , 3이면 실패 ㅠㅠ
				
				int state = memberMatchDao.loadMatchState(new MatchFindRequest(member1, member2));
				
				// 매치 오른쪽으로 스와이프이면서 match 상태가 3이 아닐경우
				matchState = (type.equals("right") && state != 3) ? state + 1 : 3;
				memberMatchDao.MatchUpdate(new MemberMatch(0, member1, member2, matchState));
				System.out.println("state : " + state);
				
				return matchState == 2 ? memberMatchDao.successMatch(new MatchFindRequest(member1, member2)) : null;
				
			} else {
				
				int state = memberMatchDao.loadMatchState(new MatchFindRequest(member2, member1));
				
				matchState = (type.equals("right") && state != 3) ? state + 1 : 3;
				memberMatchDao.MatchUpdate(new MemberMatch(0, member2, member1, matchState));
				System.out.println("state : " + state);
				
				return matchState == 2 ? memberMatchDao.successMatch(new MatchFindRequest(member2, member1)) : null;
			}
		}
	}
	
	// 채팅방에서 유저정보 불러오기
	public LoadPersonalChattingInfo loadPersonalChattingInfo(String email) throws IOException {
		
		try {
			MemberProfile profile = memberProfileDao.findByEmail(email);
			
			return new LoadPersonalChattingInfo(
					email, 
					profile.getNickname(), 
					imageGetService.getImg("profile", profile.getEmail(), 1).get(0),
					imageGetService.getType("profile", profile.getEmail(), 1).get(0));
		} catch (Exception e) {
			
			throw new MemberException("존재하지 않는 유저", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// 게시글에서 유저정보 불러오기
	public List<LoadPersonalChattingInfo> loadBoardAuthor(String emailParam) throws IOException {
		
		List<LoadPersonalChattingInfo> authorInfo = new ArrayList<>();
		// URL 디코딩
        String decodedEmailParam = URLDecoder.decode(emailParam, "UTF-8");
        
		ObjectMapper objectMapper = new ObjectMapper();
		
		List<Map<String, String>> emails = objectMapper.readValue(decodedEmailParam, new TypeReference<List<Map<String, String>>>() {});
        
		for (Map<String, String> email : emails) {
			try {
				
				MemberProfile profile = memberProfileDao.findByEmail(email.get("email"));
				
				authorInfo.add(new LoadPersonalChattingInfo(
						email.get("email"), 
						profile.getNickname(), 
						imageGetService.getImg("profile", profile.getEmail(), 1).get(0), 
						imageGetService.getType("profile", profile.getEmail(), 1).get(0)));
			} catch (Exception e) {
				
				//존재하지 않은 멤버일때
				authorInfo.add(new LoadPersonalChattingInfo(
						email.get("email"), 
						"알 수 없음", 
						null, 
						null));
			}
		}
		
		return authorInfo;
		
	}
}
