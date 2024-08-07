package com.cheeus.admin.adminReports.mapper;

import com.cheeus.admin.adminReports.dto.AdminReportDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminReportMapper {
    List<AdminReportDto> findAll();
    AdminReportDto findById(int id);
    void insert(AdminReportDto board);
    void update(AdminReportDto board);
    void delete(int id);
}
