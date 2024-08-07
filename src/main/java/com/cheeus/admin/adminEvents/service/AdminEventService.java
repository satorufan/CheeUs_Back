package com.cheeus.admin.adminEvents.service;

import com.cheeus.admin.adminEvents.dto.AdminEventDto;

import java.util.List;
import java.util.Optional;

public interface AdminEventService {
    List<AdminEventDto> findAll();
    Optional<AdminEventDto> findById(int id);
    void insert(AdminEventDto board);
    void update(AdminEventDto board);
    void delete(int id);
}
