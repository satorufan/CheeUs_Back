package com.fullstack.finalproject.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.finalproject.board.dto.BoardDto;
import com.fullstack.finalproject.board.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public List<BoardDto> getBoard(){
		return boardService.findAll();
	}
	
	@GetMapping("/{board_no}")
	public Optional<BoardDto> getBoardfind(@PathVariable int id){
		return boardService.findById(id);
	}
	@GetMapping("/review/{board_no}")
	public Optional<BoardDto> getVBoardfind(@PathVariable int board_no){
		return boardService.findByIdReview(board_no);
	}
	@GetMapping("/repls/{board_no}")
	public List<BoardDto> getBoardfindRepls(@PathVariable int board_no){
		return boardService.findByIdRepls(board_no);
	}
	
	@PostMapping("/insertCboard/")
	public void insertBoard(@RequestBody BoardDto board) {
		boardService.recruitInsert(board);
	}
	@PostMapping("/insertVboard/")
	public void insertVBoard(@RequestBody BoardDto board) {
		boardService.reviewInsert(board);
	}
	@PostMapping("/insertBoardRepl/")
	public void insertBoardRepl(@RequestBody BoardDto board) {
		boardService.insertRepl(board);
	}
	
	@PutMapping("/updateBoard/")
	public String updateBoard(@RequestBody BoardDto board) {
		Optional<BoardDto> existingReview = boardService.findByIdReview(board.getId());
		Optional<BoardDto> existing = boardService.findById(board.getId());
		if (existing.isPresent() || existingReview.isPresent()) {
			System.out.println(board);
			board.setId(board.getId());
			boardService.recruitUpdate(board);
			return "성공";
		} else {
			return "실패";
		}
	}
	
	@GetMapping("/deleteBoard/{board_no}")
	public String delete(@PathVariable int board_no) {
		Optional<BoardDto> existingC = boardService.findById(board_no);
		Optional<BoardDto> existingV = boardService.findByIdReview(board_no);
		if (existingC.isPresent()||existingV.isPresent()) {
			boardService.delete(board_no);
			return "성공";
		} else {
			return "실패";
		}
	}
	
}
