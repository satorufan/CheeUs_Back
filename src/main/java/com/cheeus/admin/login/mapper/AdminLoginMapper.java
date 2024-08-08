package com.cheeus.admin.login.mapper;

import com.cheeus.admin.login.model.AdminLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminLoginMapper {

    @Select("SELECT * FROM admin WHERE id = #{id}")
    AdminLogin findById(String id);

}
