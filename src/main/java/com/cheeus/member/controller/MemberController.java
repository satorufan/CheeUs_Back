package com.cheeus.member.controller;


import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheeus.member.domain.MemberProfile;
import com.cheeus.member.response.SignInResponse;
import com.cheeus.member.response.SignUpResponse;
import com.cheeus.member.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService service;
	
	//로그인
	@GetMapping("/signIn")
	public ResponseEntity<SignInResponse> signIn(@RequestParam("email") String email)
			throws IOException{
		//이미 가입된 이메일인지 확인
		service.existByEmail(email);

		//가입된 이메일이면 로그인 완료 Response 리턴
		return ResponseEntity.ok(service.signIn(email));
	}
	
	
	//회원가입
	@PostMapping("/signUp")
	public ResponseEntity<SignUpResponse> signUp(@RequestBody MemberProfile profile) {
		
		System.out.println("회원가입");
		
		service.signUp(profile);
		
		return ResponseEntity.ok(null);
	}
	
	
	
}
