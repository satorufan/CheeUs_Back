package com.fullstack.finalproject.board.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.fullstack.finalproject.board.dto.BoardDto;


@Mapper
public interface BoardMapper {

	List<BoardDto> findAll();

    Optional<BoardDto> findById(int id);
    Optional<BoardDto> findByIdReview(int id);
    List<BoardDto> findByIdRepls(int id);

    void insert(BoardDto board);
    void recruitInsert(BoardDto board);
    void reviewInsert(BoardDto board);
    void insertRepl(BoardDto board);

    void update(BoardDto board);
    void recruitUpdate(BoardDto board);

    void delete(int board_no);
}
