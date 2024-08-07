package com.cheeus.admin.adminEvents.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminEventDto {

	// Event 탭 DB
	private int id;
	@JsonProperty("admin_id")
	private String adminId;
	@JsonProperty("admin_name")
	private String adminName;
	private String title;
	private String title2;
	private String content;
	private String writeday;
	private int like;
	private int views;
	private String photoes;

}
