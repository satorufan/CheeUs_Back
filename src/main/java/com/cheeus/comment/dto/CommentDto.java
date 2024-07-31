package com.cheeus.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {
    private int id;
    @JsonProperty("board_id")
    private int boardId;
    @JsonProperty("repl_author_id")
    private String replAuthorId;
    @JsonProperty("parent_id")
    private int parentId;
    private int group;
    private String writeday;
    @JsonProperty("repl_content")
    private String replContent;
}