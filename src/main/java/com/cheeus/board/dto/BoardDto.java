package com.cheeus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDto {
<<<<<<< HEAD
	// 보드 DB
=======
	// 일반 게시판 DB
>>>>>>> c4de7ebf35a3db9ce7bf4246cb8f4ada2af66fd5
	private int id;
	private int authorId;
	private int category;
	private String title;
	private String content;
	private String writeday;
	private int views;
	private int like;
	private int replCnt;
	
	// Board join table 기본속성
	private int boardId;

	// FreePostPhoto 속성들
	private String photoes;
	
	// FreeBoard 속성들
	private String head;

	// Shorts 속성들
	private String media;

	// Notices 속성들
	private String createdAt;

}
