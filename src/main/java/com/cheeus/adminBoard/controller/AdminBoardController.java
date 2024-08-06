package com.cheeus.adminBoard.controller;

import com.cheeus.adminBoard.dto.AdminBoardDto;
import com.cheeus.adminBoard.service.AdminBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminBoardController {

	@Autowired
	private AdminBoardService boardService;

	@GetMapping("/AdminBoard")
	public List<AdminBoardDto> getBoard(){
		return boardService.findAll();
	}

	@GetMapping("/AdminBoard/{id}")
	public Optional<AdminBoardDto> getBoardById(@PathVariable("id") int id){
		return boardService.findById(id);
	}

	@PostMapping("/AdminBoard")
	public void insertBoard(@RequestBody AdminBoardDto board){
		boardService.insert(board);
	}

	@PutMapping("/AdminBoard/{id}")
	public String updateBoard(@RequestBody AdminBoardDto board) {
		Optional<AdminBoardDto> existing = boardService.findById(board.getId());
		if (existing.isPresent()) {
			boardService.update(board);
			return "update 성공";
		} else {
			return "update 실패";
		}
	}

	@DeleteMapping("/AdminBoard/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<AdminBoardDto> existing = boardService.findById(id);
		if (existing.isPresent()) {
			boardService.delete(id);
			return "delete 성공";
		} else {
			return "delete 실패";
		}
	}
}