package com.cheeus.admin.adminPosts.mapper;

import com.cheeus.admin.adminPosts.dto.AdminPostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminPostMapper {
    List<AdminPostDto> findAll();
    AdminPostDto findById(int id);
    void insert(AdminPostDto board);
    void update(AdminPostDto board);
    void delete(int id);
}
