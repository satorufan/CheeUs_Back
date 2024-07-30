package com.cheeus.comment.service;

import com.cheeus.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findAll();
    List<CommentDto> findByBoardId(int boardId);
    void insert(CommentDto comment);
    void update(CommentDto comment);
    void delete(int id);
}
