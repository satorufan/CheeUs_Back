package com.cheeus.admin.adminUsers.mapper;

import com.cheeus.admin.adminUsers.dto.AdminUsersDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUsersMapper {
    List<AdminUsersDto> findAll();
    AdminUsersDto findByEmail(String id);
    void insert(AdminUsersDto profileData);
    void update(AdminUsersDto profileData);
    void delete(String id);
}
