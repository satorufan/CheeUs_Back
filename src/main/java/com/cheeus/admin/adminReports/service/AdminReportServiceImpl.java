package com.cheeus.admin.adminReports.service;

import com.cheeus.admin.adminReports.mapper.AdminReportMapper;
import com.cheeus.admin.adminReports.dto.AdminReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminReportServiceImpl implements AdminReportService {

	@Autowired
	private AdminReportMapper adminReportMapper;

	@Override
	public List<AdminReportDto> findAll() {
		return adminReportMapper.findAll();
	}

	@Override
	public Optional<AdminReportDto> findById(int id) {
		return Optional.ofNullable(adminReportMapper.findById(id));
	}

	@Override
	public void insert(AdminReportDto board) {
		adminReportMapper.insert(board);
	}


	@Override
	public void update(AdminReportDto board) {
		adminReportMapper.update(board);
	}

	@Override
	public void delete(int id) {
		adminReportMapper.delete(id);
	}

}
