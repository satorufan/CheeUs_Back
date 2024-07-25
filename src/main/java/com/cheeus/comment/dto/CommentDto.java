package com.cheeus.comment.dto;

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
    private int boardId;
    private String replAuthorId;
    private int group;
    private String writeday;
    private String replContent;

}