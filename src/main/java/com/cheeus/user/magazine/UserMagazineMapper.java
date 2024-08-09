package com.cheeus.user.magazine;

import com.cheeus.admin.adminMagazines.dto.AdminMagazineDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMagazineMapper {
    List<AdminMagazineDto> findAll();
    AdminMagazineDto findById(int id);
}
