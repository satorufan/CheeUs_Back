package com.cheeus.adminReport.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminReportDto {

	// 신고게시판 Db
	private int id;
	@JsonProperty("author_id")
	private String authorId;
	private String nickname;
	private int category;
	private String title;
	private String content;
	private String writeday;
	private int views;
	private int like;
	@JsonProperty("repl_cnt")
	private int replCnt;

}
