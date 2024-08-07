package com.cheeus.admin.adminPosts.service;

import com.cheeus.admin.adminPosts.dto.AdminPostDto;

import java.util.List;
import java.util.Optional;

public interface AdminPostService {
    List<AdminPostDto> findAll();
    Optional<AdminPostDto> findById(int id);
    void insert(AdminPostDto board);
    void update(AdminPostDto board);
    void delete(int id);
}
