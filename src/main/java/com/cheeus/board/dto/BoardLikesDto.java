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
public class BoardLikesDto {
    @JsonProperty("post_id")
    private int id;
    @JsonProperty("user_id")
    private String userEmail;
}
