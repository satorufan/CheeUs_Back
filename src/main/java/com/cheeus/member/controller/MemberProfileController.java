package com.cheeus.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheeus.member.service.MemberProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class MemberProfileController {
	
	private final MemberProfileService profileService;
	
	@GetMapping
	public ResponseEntity<?> loadProfile(@RequestParam("email") String email) {
		System.out.println(email);
		
		return ResponseEntity.ok(profileService.findByEmail(email));
	}

}
