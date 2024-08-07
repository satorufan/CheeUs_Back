package com.cheeus.admin.adminReports.service;

import com.cheeus.admin.adminReports.dto.AdminReportDto;

import java.util.List;
import java.util.Optional;

public interface AdminReportService {
    List<AdminReportDto> findAll();
    Optional<AdminReportDto> findById(int id);
    void insert(AdminReportDto board);
    void update(AdminReportDto board);
    void delete(int id);
}
