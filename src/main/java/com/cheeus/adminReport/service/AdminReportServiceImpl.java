package com.cheeus.adminReport.service;

import com.cheeus.adminReport.dto.AdminReportDto;
import com.cheeus.adminReport.mapper.AdminReportMapper;
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
