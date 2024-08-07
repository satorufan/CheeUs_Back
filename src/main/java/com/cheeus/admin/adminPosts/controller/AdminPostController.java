package com.cheeus.admin.adminPosts.controller;

import com.cheeus.admin.adminPosts.dto.AdminPostDto;
import com.cheeus.admin.adminPosts.service.AdminPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminPostController {

	@Autowired
	private AdminPostService boardService;

	@GetMapping("/AdminPost")
	public List<AdminPostDto> getBoard(){
		return boardService.findAll();
	}

	@GetMapping("/AdminPost/{id}")
	public Optional<AdminPostDto> getBoardById(@PathVariable("id") int id){
		return boardService.findById(id);
	}

	@PostMapping("/AdminPost")
	public void insertBoard(@RequestBody AdminPostDto board){
		boardService.insert(board);
	}

	@PutMapping("/AdminPost/{id}")
	public String updateBoard(@RequestBody AdminPostDto board) {
		Optional<AdminPostDto> existing = boardService.findById(board.getId());
		if (existing.isPresent()) {
			boardService.update(board);
			return "update 성공";
		} else {
			return "update 실패";
		}
	}

	@DeleteMapping("/AdminPost/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<AdminPostDto> existing = boardService.findById(id);
		if (existing.isPresent()) {
			boardService.delete(id);
			return "delete 성공";
		} else {
			return "delete 실패";
		}
	}
}