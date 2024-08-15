package com.cheeus.admin.adminUsers.service;

import com.cheeus.admin.adminUsers.dto.AdminUsersDto;

import java.util.List;

public interface AdminUsersService {
    List<AdminUsersDto> findAll();

    AdminUsersDto findByEmail(String id);

    void save(AdminUsersDto profileData);

    void delete(String id);
}
