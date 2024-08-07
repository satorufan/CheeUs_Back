package com.cheeus.admin.adminMagazines.mapper;

import com.cheeus.admin.adminMagazines.dto.AdminMagazineDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMagazineMapper {
    List<AdminMagazineDto> findAll();
    AdminMagazineDto findById(int id);
    void insert(AdminMagazineDto board);
    void update(AdminMagazineDto board);
    void delete(int id);
}
