package com.cheeus.member.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cheeus.member.domain.MemberPopularity;
import com.cheeus.member.domain.MemberProfile;
import com.cheeus.member.exception.MemberException;
import com.cheeus.member.repository.MemberProfileDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberProfileService {
	
	private final MemberProfileDao profileDao;
	
//	// 가입 시 이미 존재하는 회원인지 확인
//		public HttpStatus existByEmail(String email) {
//			
//			Integer existMember = profileDao.existByEmail(email);
//			if (existMember == 0) {
//				throw new MemberException("존재하지 않는 이메일입니다.", HttpStatus.BAD_REQUEST);
//			}
//			return HttpStatus.OK;
//		}
	
	// 닉네임 중복 확인
	public HttpStatus existNickname (String nickname) {
		
		Integer existNickname = profileDao.existNickname(nickname);
		
		if (existNickname > 0) {
			throw new MemberException("이미 존재하는 닉네임 입니다.", HttpStatus.BAD_REQUEST);
		}
		
		return HttpStatus.OK;
	}
	
	
	// 회원 정보 불러오기
	public MemberProfile findByEmail (String email) {
		System.out.println(email);
		
		MemberProfile findMember = profileDao.findByEmail(email);
		
		return findMember;
	}
	
	
	// 회원 수정
	public MemberProfile updateMember (MemberProfile membeProfile) {
		
		profileDao.updateMember(membeProfile);
		
		return membeProfile;
	}
	
	
	// 회원 탈퇴
	public HttpStatus deleteMember (String email) {
		
		MemberProfile findMember = findByEmail(email);
		
		if (findMember == null) {
			throw new MemberException("존재하지 않은 아이디입니다.", HttpStatus.BAD_REQUEST);
		}
		
		profileDao.deleteMember(email);
		
		return HttpStatus.OK;
	}
	
	
	
	
	
	
	
	
	// 좋아요 개수 불러오기
	public Integer findPopularity (String email) {
		
		return profileDao.countPopularity(email);
	}
	
	
	// 내가 좋아요 눌렀는가
	public boolean isClickedPopularity (MemberPopularity popularity) {
		
		//liker는 현재 로그인 유저, 나.
		if (popularity.getLiker() == null) {
			throw new MemberException("로그인 해주세요.", HttpStatus.BAD_REQUEST);
		} else {
			
			Integer check = profileDao.existPopularity(popularity);
			
			if (check == 0) {
				// 좋아요 안눌름
				return false;
			}
			
			// 눌름
			return true;
		}
	}
	
	// 좋아요 추가/삭제
	public void addPopularity (MemberPopularity popularity) {
		
		if (!isClickedPopularity(popularity)) {
			// 좋아요가 없으면 좋아요 추가
			profileDao.addPopularity(popularity);
			
		} else {
			// 있으면 좋아요 삭제
			profileDao.deletePopularity(popularity);
		}
	}
	

}
