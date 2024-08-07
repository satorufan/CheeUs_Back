package com.cheeus.member.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cheeus.member.domain.MemberPopularity;
import com.cheeus.member.domain.MemberProfile;
import com.cheeus.member.domain.MemberScrap;
import com.cheeus.member.request.LocationRequest;
import com.cheeus.member.response.MyInsertedPostResponse;

@Mapper
@Repository
public interface MemberProfileDao {
	
	//회원정보 불러오기
	MemberProfile findByEmail(String email);
	
	//회원가입
	void createMember(MemberProfile profile);
	
	//닉네임 중복확인
	Integer existNickname(String nickname);
	
	//회원수정
	void updateMember (MemberProfile profile);
	
	//회원탈퇴
	String deleteMember (String email);
	
	
	//위치 동의
	void allowLocation(LocationRequest locationRequest);
	//매칭 동의
	void allowMatching(String email);
	
	
	//좋아요 목록 불러오기
	ArrayList<MemberPopularity> findPopularity (String email);
	
	//좋아요 개수 불러오기
	Integer countPopularity (String email);
	
	//내가 좋아요 눌렀는지 확인
	Integer existPopularity (MemberPopularity popularity);
	
	//좋아요 추가
	void addPopularity (MemberPopularity popularity);
	
	//좋아요 삭제
	void deletePopularity (MemberPopularity popularity);
	
	
	// 찜목록 불러오기
	ArrayList<MemberScrap> findMyScrap(String email);
	
	// 찜 확인
	Integer isScrapped (MemberScrap memberScrap);
	
	// 찜하기
	void addScrap(MemberScrap memberScrap);
	
	// 찜삭제
	void deleteScrap(MemberScrap memberScrap);
	
	
	// 내가 쓴글
	ArrayList<MyInsertedPostResponse> findMyPost(String email);
	
}
