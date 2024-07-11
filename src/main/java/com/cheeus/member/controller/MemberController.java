package com.cheeus.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheeus.config.auth.token.JWTUtil;
import com.cheeus.member.domain.Member;
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
	
	@GetMapping
	public String test() {
		System.out.println("Hi");
		return "Hello Member!";
	}
	
	@GetMapping("/token")
	public String token(@RequestHeader("Authorization") String auth) {
		System.out.println("Controller - token : " + auth);
		return "Hello Member!";
	}
	
	//토큰 확인 로직
	@GetMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String token, @RequestParam("email") String email, HttpServletResponse response) {
		System.out.println(email);
        return ResponseEntity.ok(response.getHeaders("Authorization"));
    }
	
	@GetMapping("/exist")
	public ResponseEntity<?> existByEmail(@RequestParam("email") String email) {
		System.out.println("email 중복 요청 : " + email);
		
		HttpStatus status = service.existByEmail(email);
		return new ResponseEntity<>(status);
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<SignInResponse> signIn(@RequestBody Member member) {
		System.out.println("로그인 요청 : " + member);
		
		return ResponseEntity.ok(service.signIn(member));
	}
	
	
	@PostMapping("/signUp")
	public ResponseEntity<SignUpResponse> signUp(@RequestBody Member member) {
		System.out.println("회원 가입 요청 : " + member);
		
		return ResponseEntity.ok(service.signUp(member));
	}
	
}
