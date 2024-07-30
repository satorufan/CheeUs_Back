package com.cheeus.board.controller;

import com.cheeus.board.dto.BoardDto;
import com.cheeus.board.service.BoardService;
import com.cheeus.firebase.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private ImageUploadService imageUploadService;

	@GetMapping("/")
	public List<BoardDto> getBoard(){
		return boardService.findAll();
	}

	@GetMapping("/freeboard")
	public List<BoardDto> getFreeboard(){
		return boardService.findAllFreeboard();
	}

	@GetMapping("/shortform")
	public List<BoardDto> getShortfoam(){
		return boardService.findAllShortform();
	}

	@GetMapping("/eventboard")
	public List<BoardDto> getEventboard(){
		return boardService.findAllEventboard();
	}

	/*
	@GetMapping("/{id}")
	public Optional<BoardDto> getBoardById(@PathVariable int id){
		return boardService.findById(id);
	}
	*/

	@PostMapping("/insert")
	public void insertBoard(@RequestParam("board") String boardJson,
							@RequestParam("file") Optional<MultipartFile> file) throws IOException {
		// JSON -> BoardDto 변환
		ObjectMapper objectMapper = new ObjectMapper();
		BoardDto board = objectMapper.readValue(boardJson, BoardDto.class);

		// 파일이 있을 경우 업로드 처리
		if (file.isPresent()) {
			String fileName = file.get().getOriginalFilename();
			File tempFile = imageUploadService.convertToFile(file.get(), fileName);
			String fileUrl = imageUploadService.uploadFile(tempFile, fileName, file.get().getContentType());
			board.setMedia(fileUrl);

			// 임시 파일 삭제
			tempFile.delete();
		}

		boardService.insert(board);
	}

	@PutMapping("/update/{id}")
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