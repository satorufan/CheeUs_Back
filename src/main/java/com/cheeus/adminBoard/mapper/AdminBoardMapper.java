package com.cheeus.adminBoard.mapper;

import com.cheeus.adminBoard.dto.AdminBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminBoardMapper {
    List<AdminBoardDto> findAll();
    List<AdminBoardDto> findAllFreeboard();
    List<AdminBoardDto> findAllShortform();
    List<AdminBoardDto> findAllEventboard();
    AdminBoardDto findById(int id);
    void insert(AdminBoardDto board);
    void update(AdminBoardDto board);
    void delete(int id);
}
