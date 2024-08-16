package com.cheeus.user.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class UserEventController {

	@Autowired
	private UserEventService eventService;

	@GetMapping("/Event")
	public List<UserEventDto> getBoard(){
		return eventService.findAll();
	}

	@GetMapping("/Event/{id}")
	public Optional<UserEventDto> getBoardById(@PathVariable("id") int id){
		return eventService.findById(id);
	}


	@PutMapping("/Event/toggleLike/{eventId}")
	public ResponseEntity<?> toggleLike(@PathVariable("eventId") int eventId, @RequestParam("memberEmail") String memberEmail) {
		try {
			Integer updatedLikeCount = eventService.toggleLike(eventId, memberEmail);

			// isLikedByUser 메서드가 null을 반환할 수 있는 경우를 방지
			Boolean isLiked = eventService.isLikedByUser(eventId, memberEmail);
			if (isLiked == null) {
				isLiked = false;  // 기본값 설정
			}

			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			response.put("updatedLikeCount", updatedLikeCount);
			response.put("isLiked", isLiked);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to toggle like for post: " + eventId + ". Error: " + e.getMessage());
		}
	}

	// 조회수 조회 & 증가
	@PutMapping("/Event/incrementView/{id}")
	public ResponseEntity<?> incrementViewCount(@PathVariable("id") int id) {
		try {
			int updatedViewCount = eventService.incrementViewCount(id);
			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			response.put("updatedViewCount", updatedViewCount);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to increment view count for post: " + id);
		}
	}

}