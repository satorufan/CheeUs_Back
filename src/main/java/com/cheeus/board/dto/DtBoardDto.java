package com.cheeus.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DtBoardDto {
	// DT보드 DB
	private int id;
	private int authorId;
	private String location;
	private String address;
	private String lat;
	private String lng;
	private String time;
	private String title;
	private String content;
	private String writeday;
	private int views;
	private int like;

}
