package com.cheeus.admin.login.mapper;

import com.cheeus.admin.login.model.AdminLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminLoginMapper {
    AdminLogin findAdminById(String id);
}
