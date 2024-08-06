package com.cheeus.adminReport.controller;

import com.cheeus.adminReport.dto.AdminReportDto;
import com.cheeus.adminReport.service.AdminReportService;
import com.cheeus.firebase.ImageUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminReportController {

	@Autowired
	private AdminReportService boardService;

	@Autowired
	private ImageUploadService imageUploadService;

	@GetMapping("/AdminReport")
	public List<AdminReportDto> getBoard(){
		return boardService.findAll();
	}


	@GetMapping("/AdminReport/{id}")
	public Optional<AdminReportDto> getBoardById(@PathVariable int id){
		return boardService.findById(id);
	}


	@PostMapping("/AdminReport")
	public void insertBoard(@RequestParam("board") String boardJson,
							@RequestParam("file") Optional<MultipartFile> file) throws IOException {
		// JSON -> BoardDto 변환
		ObjectMapper objectMapper = new ObjectMapper();
		AdminReportDto board = objectMapper.readValue(boardJson, AdminReportDto.class);

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

	@PutMapping("/AdminReport/{id}")
	public String updateBoard(@RequestBody AdminReportDto board) {
		Optional<AdminReportDto> existing = boardService.findById(board.getId());
		if (existing.isPresent()) {
			boardService.update(board);
			return "update 성공";
		} else {
			return "update 실패";
		}
	}

	@DeleteMapping("/AdminReport/{id}")
	public String delete(@PathVariable int id) {
		Optional<AdminReportDto> existing = boardService.findById(id);
		if (existing.isPresent()) {
			boardService.delete(id);
			return "delete 성공";
		} else {
			return "delete 실패";
		}
	}
}