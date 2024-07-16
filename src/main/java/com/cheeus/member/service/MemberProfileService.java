package com.cheeus.member.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
		
		return HttpStatus.OK;
	}
	
	// 회원 정보 불러오기
	public MemberProfile findByEmail (String email) {
		
		MemberProfile findMember = profileDao.findByEmail(email);
		System.out.println(findMember==null);
		
		return findMember;
	}

}
