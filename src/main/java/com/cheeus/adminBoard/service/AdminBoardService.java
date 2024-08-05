package com.cheeus.adminBoard.service;

import com.cheeus.adminBoard.dto.AdminBoardDto;

import java.util.List;
import java.util.Optional;

public interface AdminBoardService {
    List<AdminBoardDto> findAll();
    List<AdminBoardDto> findAllFreeboard();
    List<AdminBoardDto> findAllShortform();
    List<AdminBoardDto> findAllEventboard();
    Optional<AdminBoardDto> findById(int id);
    void insert(AdminBoardDto board);
    void update(AdminBoardDto board);
    void delete(int id);
}
