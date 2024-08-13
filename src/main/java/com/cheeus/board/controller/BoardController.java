package com.cheeus.board.controller;

import com.cheeus.board.dto.BoardDto;
import com.cheeus.board.mapper.BoardMapper;
import com.cheeus.board.response.BoardResponse;
import com.cheeus.board.service.BoardService;
import com.cheeus.firebase.ImageGetService;
import com.cheeus.firebase.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ImageGetService imageGetService;

	@Autowired
	private ImageUploadService imageUploadService;

	@GetMapping("/")
	public List<BoardDto> getBoard(){
		return boardService.findAll();
	}

	@GetMapping("/freeboard")
	public ResponseEntity<Map<String, Object>> getFreeboard() {
	    List<BoardDto> boardList = boardService.findAllFreeboard();
	    int maxId = boardService.getMaxIdFB();

	    Map<String, Object> response = new HashMap<>();
	    response.put("boardList", boardList);
	    response.put("maxId", maxId);

	    return ResponseEntity.ok(response);
	}

	@GetMapping("/shortform")
	public List<BoardDto> getShortfoam () {
		
		List<BoardDto> boards = boardService.findAllShortform();
		return boards;
	}
	
	@GetMapping("/shortform/media")
	public List<BoardResponse> getShortfoamMedia (@RequestParam("board") String boardParam ) throws IOException {
		
		List<BoardResponse> allOfBoardWithMedia = new ArrayList<>();
		// URL 디코딩
        String decodedBoardParam = URLDecoder.decode(boardParam, "UTF-8");
        
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Integer>> boards = objectMapper.readValue(decodedBoardParam, new TypeReference<List<Map<String, Integer>>>() {});
        
		for (Map<String, Integer> board : boards) {
			List<byte[]> medias = imageGetService.getImg("board/shortform/", board.get("id") + "", board.get("photoes"));
			List<String> types = imageGetService.getType("board%2Fshortform", board.get("id") + "", board.get("photoes"));
			
			allOfBoardWithMedia.add(new BoardResponse(board.get("id"), medias, types));
		}
		return allOfBoardWithMedia;
	}

	@GetMapping("/eventboard")
	public ResponseEntity<Map<String, Object>> getEventboard() {
	    List<BoardDto> boardList = boardService.findAllEventboard();
	    int maxId = boardService.getMaxIdEB();

	    Map<String, Object> response = new HashMap<>();
	    response.put("boardList", boardList);
	    response.put("maxId", maxId);

	    return ResponseEntity.ok(response);
	}
	
	

	/*
	@GetMapping("/{id}")
	public Optional<BoardDto> getBoardById(@PathVariable int id){
		return boardService.findById(id);
	}
	*/

	private final ArrayList<String> category = new ArrayList<>() {
		private static final long serialVersionUID = 8888;
	{
		add("freeboard/");
		add("shortform/");
		add("eventboard/");
	}};
	@PostMapping("/insert")
	public void insertBoard(@RequestParam("board") String boardJson,
							@RequestParam("file") Optional<MultipartFile> file) throws IOException {
		// JSON -> BoardDto 변환
		ObjectMapper objectMapper = new ObjectMapper();
		BoardDto board = objectMapper.readValue(boardJson, BoardDto.class);
		
		int id = getBoard().size() > 0 ? boardService.findLatest() : 0;

		// 파일이 있을 경우 업로드 처리
		if (file.isPresent()) {
			String fileName = file.get().getOriginalFilename();
			File tempFile = imageUploadService.convertToFile(file.get(), fileName);
			String fileUrl = imageUploadService.uploadFile(
					tempFile, 
					"board/" + category.get(board.getCategory()-1) + (id + 1) + "/" + 0, 
					file.get().getContentType());
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
	public String delete(@PathVariable("id") int id) {
		Optional<BoardDto> existing = boardService.findById(id);
		if (existing.isPresent()) {
			boardService.delete(id);
			return "delete 성공";
		} else {
			return "delete 실패";
		}
	}
}