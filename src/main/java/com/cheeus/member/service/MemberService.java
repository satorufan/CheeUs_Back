package com.cheeus.member.service;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;
=======
>>>>>>> c4de7ebf35a3db9ce7bf4246cb8f4ada2af66fd5

import com.cheeus.member.domain.MemberProfile;
import com.cheeus.member.exception.MemberException;
import com.cheeus.member.repository.MemberDao;
import com.cheeus.member.repository.MemberProfileDao;
import com.cheeus.member.response.SignInResponse;
import com.cheeus.member.response.SignUpResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberDao dao;
	private final MemberProfileDao profileDao;

	
	// 가입 시 이미 존재하는 회원인지 확인
	public HttpStatus existByEmail(String email) {
		
		Integer existMember = dao.existByEmail(email);
		if (existMember == 0) {
			throw new MemberException("존재하지 않는 이메일입니다.", HttpStatus.BAD_REQUEST);
		}
		return HttpStatus.OK;
	}
	

	// 로그인
	public SignInResponse signIn(String email) throws IOException {
		// 회원정보 존재 확인
		Integer existMember = dao.existByEmail(email);
		String nickname = profileDao.findByEmail(email).getNickname();
		
		if (existMember == 0) {
			return new SignInResponse(null, null);
		}
		
		return new SignInResponse(nickname, email);
	}
	
	
	
	// 회원가입
<<<<<<< HEAD
	@Transactional
=======
>>>>>>> c4de7ebf35a3db9ce7bf4246cb8f4ada2af66fd5
	public SignUpResponse signUp(MemberProfile profile) {
		
		dao.createMember(profile.getEmail());	//member DB에 저장
		
<<<<<<< HEAD
=======
		//임시 사진 갯수 설정
		profile.setPhoto(1);
>>>>>>> c4de7ebf35a3db9ce7bf4246cb8f4ada2af66fd5
		profileDao.createMember(profile);	//profile DB에 저장
		
		return new SignUpResponse(profile.getEmail());
		
	}
	
<<<<<<< HEAD
	// 회원 탈퇴
	public void deleteMember(String email) {
		dao.deleteMember(email);
	}
=======
	//닉네임 중복확인
	
>>>>>>> c4de7ebf35a3db9ce7bf4246cb8f4ada2af66fd5
	
	
}
