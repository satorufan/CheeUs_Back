package com.cheeus.admin.adminEvents.mapper;

import com.cheeus.admin.adminEvents.dto.AdminEventDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminEventMapper {
    List<AdminEventDto> findAll();
    AdminEventDto findById(int id);
    void insert(AdminEventDto board);
    void update(AdminEventDto board);
    void delete(int id);
}
