package com.fullstack.sfinal.cheeusbackend.board.mapper;

import com.fullstack.finalproject.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDto> findAll();
    BoardDto findById(int id);
    void insert(BoardDto board);
    void update(BoardDto board);
    void delete(int id);
}
