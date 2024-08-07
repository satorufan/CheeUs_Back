package com.cheeus.admin.adminMagazines.service;

import com.cheeus.admin.adminMagazines.dto.AdminMagazineDto;

import java.util.List;
import java.util.Optional;

public interface AdminMagazineService {
    List<AdminMagazineDto> findAll();
    Optional<AdminMagazineDto> findById(int id);
    void insert(AdminMagazineDto board);
    void update(AdminMagazineDto board);
    void delete(int id);
}
