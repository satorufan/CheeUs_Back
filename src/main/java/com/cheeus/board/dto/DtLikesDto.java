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

public class DtLikesDto {
    @JsonProperty("post_id")
    private int postId;
    @JsonProperty("user_id")
    private String userId;
}
