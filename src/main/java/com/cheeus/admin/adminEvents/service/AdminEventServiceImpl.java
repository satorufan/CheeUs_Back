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
	private AdminEventMapper eventMapper;

	@Override
	public List<AdminEventDto> findAll() {
		return eventMapper.findAll();
	}

	@Override
	public Optional<AdminEventDto> findById(int id) {
		return Optional.ofNullable(eventMapper.findById(id));
	}

	@Override
	public void insert(AdminEventDto board) {
		eventMapper.insert(board);
	}

	@Override
	public void update(AdminEventDto board) {
		eventMapper.update(board);
	}

	@Override
	public void delete(int id) {
		eventMapper.delete(id);
	}

}
