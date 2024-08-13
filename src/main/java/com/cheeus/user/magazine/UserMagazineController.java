package com.cheeus.user.magazine;

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
public class UserMagazineController {

	@Autowired
	private UserMagazineService magazineService;

	@GetMapping("/Magazine")
	public List<UserMagazineDto> getBoard(){
		return magazineService.findAll();
	}

	@GetMapping("/Magazine/{id}")
	public Optional<UserMagazineDto> getBoardById(@PathVariable("id") int id){
		return magazineService.findById(id);
	}

	@PutMapping("/Magazine/toggleLike/{magazineId}")
	public ResponseEntity<?> toggleLike(@PathVariable("magazineId") int magazineId, @RequestParam("memberEmail") String memberEmail) {
		try {
			Integer updatedLikeCount = magazineService.toggleLike(magazineId, memberEmail);

			// isLikedByUser 메서드가 null을 반환할 수 있는 경우를 방지
			Boolean isLiked = magazineService.isLikedByUser(magazineId, memberEmail);
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
					.body("Failed to toggle like for post: " + magazineId + ". Error: " + e.getMessage());
		}
	}
}