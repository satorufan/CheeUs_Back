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
	private AdminReportMapper boardMapper;

	@Override
	public List<AdminReportDto> findAll() {
		return boardMapper.findAll();
	}

	@Override
	public List<AdminReportDto> findAllFreeboard() {
		return boardMapper.findAllFreeboard();
	}

	@Override
	public List<AdminReportDto> findAllShortform() {
		return boardMapper.findAllShortform();
	}

	@Override
	public List<AdminReportDto> findAllEventboard() {
		return boardMapper.findAllEventboard();
	}

	@Override
	public Optional<AdminReportDto> findById(int id) {
		return Optional.ofNullable(boardMapper.findById(id));
	}

	@Override
	public void insert(AdminReportDto board) {
		boardMapper.insert(board);
	}


	@Override
	public void update(AdminReportDto board) {
		boardMapper.update(board);
	}

	@Override
	public void delete(int id) {
		boardMapper.delete(id);
	}

}
