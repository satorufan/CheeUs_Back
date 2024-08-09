package com.cheeus.user.magazine;

import com.cheeus.admin.adminMagazines.dto.AdminMagazineDto;

import java.util.List;
import java.util.Optional;

public interface UserMagazineService {
    List<AdminMagazineDto> findAll();
    Optional<AdminMagazineDto> findById(int id);
}
