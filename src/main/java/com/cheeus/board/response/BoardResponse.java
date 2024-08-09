package com.cheeus.board.response;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cheeus.board.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

	private int id;
	private List<byte[]> medias;
	private List<String> types;
	
	
}
