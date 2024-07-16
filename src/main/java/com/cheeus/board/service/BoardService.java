package com.cheeus.board.service;

import com.cheeus.board.dto.BoardDto;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<BoardDto> findAll();
    Optional<BoardDto> findById(int id);
    void insert(BoardDto board);
    void update(BoardDto board);
    void delete(int id);
}
