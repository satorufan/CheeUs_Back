package com.cheeus.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberScrap {

	private String memberEmail;
	private int boardId;
	private int togetherId;
	private int eventId;
	private int magazineId;
	private String title;
	private String url;
}
