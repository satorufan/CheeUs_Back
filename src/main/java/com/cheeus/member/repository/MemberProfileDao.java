package com.cheeus.member.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.cheeus.member.domain.MemberPopularity;
=======
>>>>>>> c4de7ebf35a3db9ce7bf4246cb8f4ada2af66fd5
import com.cheeus.member.domain.MemberProfile;

@Mapper
@Repository
public interface MemberProfileDao {

	//회원정보 불러오기
<<<<<<< HEAD
	MemberProfile findByEmail(String email);
	
	//회원가입
	void createMember(MemberProfile profile);
	
	//닉네임 중복확인
	Integer existNickname(String nickname);
=======
	MemberProfile findByEmail (String email);
	
	//회원가입
	void createMember (MemberProfile profile);
	//닉네임 중복확인
	Integer existNickname (String nickname);
>>>>>>> c4de7ebf35a3db9ce7bf4246cb8f4ada2af66fd5
	
	//회원수정
	void updateMember (MemberProfile profile);
	
	//회원탈퇴
<<<<<<< HEAD
	String deleteMember (String email);
	
	
	
	//좋아요 개수 불러오기
	Integer countPopularity (String email);
	
	//내가 좋아요 눌렀는지 확인
	Integer existPopularity (MemberPopularity popularity);
	
	//좋아요 추가
	void addPopularity (MemberPopularity popularity);
	
	//좋아요 삭제
	void deletePopularity (MemberPopularity popularity);
	
=======
	String DeleteMember (String email);
>>>>>>> c4de7ebf35a3db9ce7bf4246cb8f4ada2af66fd5
	
}
