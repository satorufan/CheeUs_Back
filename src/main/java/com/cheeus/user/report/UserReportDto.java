package com.cheeus.user.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserReportDto {

	// 신고게시판 DB
	private int id;
	@JsonProperty("reporter_id")
	private String reporterId;
	@JsonProperty("reported_id")
	private String reportedId;
	private String content;
	private String writeday;
}
