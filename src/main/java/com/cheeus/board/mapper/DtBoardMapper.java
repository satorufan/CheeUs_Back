package com.cheeus.board.mapper;

import com.cheeus.board.dto.DtBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DtBoardMapper {
    List<DtBoardDto> findAll();
    DtBoardDto findById(int id);
    void insert(DtBoardDto board);
    void update(DtBoardDto board);
    void delete(int id);
}
