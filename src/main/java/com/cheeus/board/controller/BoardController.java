package com.cheeus.board.controller;

import com.cheeus.board.dto.BoardDto;
import com.cheeus.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public List<BoardDto> getBoard(){
		return boardService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<BoardDto> getBoardById(@PathVariable int id){
		return boardService.findById(id);
	}

	@PostMapping("/insert")
	public void insertBoard(@RequestBody BoardDto board) {
		boardService.insert(board);
	}

	@PutMapping("/update")
	public String updateBoard(@RequestBody BoardDto board) {
		Optional<BoardDto> existing = boardService.findById(board.getId());
		if (existing.isPresent()) {
			boardService.update(board);
			return "update 성공";
		} else {
			return "update 실패";
		}
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		Optional<BoardDto> existing = boardService.findById(id);
		if (existing.isPresent()) {
			boardService.delete(id);
			return "delete 성공";
		} else {
			return "delete 실패";
		}
	}
}

