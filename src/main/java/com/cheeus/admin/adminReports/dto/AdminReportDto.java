package com.cheeus.admin.adminReports.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminReportDto {

	// 신고게시판 DB
	private int id;
	@JsonProperty("reporter_id")
	private String reporterId;
	@JsonProperty("reported_id")
	private String reportedId;
	private String content;
	private String writeday;
}
