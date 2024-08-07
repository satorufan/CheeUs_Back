package com.cheeus.admin.adminEvents.service;

import com.cheeus.admin.adminEvents.dto.AdminEventDto;
import com.cheeus.admin.adminEvents.mapper.AdminEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminEventServiceImpl implements AdminEventService {

	@Autowired
	private AdminEventMapper boardMapper;

	@Override
	public List<AdminEventDto> findAll() {
		return boardMapper.findAll();
	}

	@Override
	public Optional<AdminEventDto> findById(int id) {
		return Optional.ofNullable(boardMapper.findById(id));
	}

	@Override
	public void insert(AdminEventDto board) {
		boardMapper.insert(board);
	}

	@Override
	public void update(AdminEventDto board) {
		boardMapper.update(board);
	}

	@Override
	public void delete(int id) {
		boardMapper.delete(id);
	}

}
