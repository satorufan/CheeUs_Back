package com.cheeus.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	@JsonProperty("author_id")
	private String authorId;
	private String location;
	private String address;
	private String latitude;
	private String longitude;
	private String time;
	private String title;
	private String content;
	private String writeday;
	private int views;
	private int like;

}
