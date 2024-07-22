package com.cheeus.member.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheeus.member.service.MemberMatchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MemberMatchController {
	
	private final MemberMatchService mamberMatchService;

	@GetMapping
	public ResponseEntity<?> loadProfiles () {
		System.out.println("load profiles");
		try {
            // Return response with HTTP status 200 OK
			return ResponseEntity.ok(mamberMatchService.findAll());
        } catch (Exception e) {
            // Handle IOException appropriately
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to load profile and image: " + e.getMessage());
        }
	}
}
