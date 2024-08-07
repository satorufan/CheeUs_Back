package com.cheeus.admin.adminUsers.service;

import com.cheeus.admin.adminUsers.dto.AdminUsersDto;

import java.util.List;

public interface AdminUsersService {
    List<AdminUsersDto> findAll();

    List<AdminUsersDto> findByEmail(String email);

    void save(AdminUsersDto profileData);

    void delete(String email);
}
