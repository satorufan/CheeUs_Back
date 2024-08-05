package com.cheeus.board.controller;

import com.cheeus.board.dto.DtBoardDto;
import com.cheeus.board.service.DtBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dtBoard")
public class DtBoardController {

	@Autowired
	private DtBoardService boardService;

	@GetMapping("/")
	public List<DtBoardDto> getBoard(){
		return boardService.findAll();
	}

	@GetMapping("/post/{id}")
	public Optional<DtBoardDto> getBoardById(@PathVariable("id") int id){
		return boardService.findById(id);
	}

	@PostMapping("/insert")
	public int insertBoard(@RequestBody DtBoardDto board) {
		boardService.insert(board);
		return boardService.responseForInsert();
	}

	@PutMapping("/update")
	public String updateBoard(@RequestBody DtBoardDto board) {
		Optional<DtBoardDto> existing = boardService.findById(board.getId());
		if (existing.isPresent()) {
			boardService.update(board);
			return "update 성공";
		} else {
			return "update 실패";
		}
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		Optional<DtBoardDto> existing = boardService.findById(id);
		if (existing.isPresent()) {
			boardService.delete(id);
			return id + "번 게시글 delete 성공";
		} else {
			return "delete 실패";
		}
	}
}

