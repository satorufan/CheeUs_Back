package com.cheeus.member.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cheeus.member.domain.MemberProfile;

@Mapper
@Repository
public interface MemberProfileDao {

	//회원정보 불러오기
	MemberProfile findByEmail (String email);
	
	//회원가입
	void createMember (MemberProfile profile);
	//닉네임 중복확인
	Integer existNickname (String nickname);
	
	//회원수정
	void updateMember (MemberProfile profile);
	
	//회원탈퇴
	String DeleteMember (String email);
	
}
