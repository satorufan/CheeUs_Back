package com.cheeus.comment.mapper;

import com.cheeus.comment.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDto> findAll();
    List<CommentDto> findByBoardId(int boardId);
    void insert(CommentDto comment);
    void update(CommentDto comment);
    void delete(int id);
}
