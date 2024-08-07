package com.cheeus.admin.adminMagazines.service;

import com.cheeus.admin.adminMagazines.dto.AdminMagazineDto;
import com.cheeus.admin.adminMagazines.mapper.AdminMagazineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminMagazineServiceImpl implements AdminMagazineService {

	@Autowired
	private AdminMagazineMapper boardMapper;

	@Override
	public List<AdminMagazineDto> findAll() {
		return boardMapper.findAll();
	}

	@Override
	public Optional<AdminMagazineDto> findById(int id) {
		return Optional.ofNullable(boardMapper.findById(id));
	}

	@Override
	public void insert(AdminMagazineDto board) {
		boardMapper.insert(board);
	}

	@Override
	public void update(AdminMagazineDto board) {
		boardMapper.update(board);
	}

	@Override
	public void delete(int id) {
		boardMapper.delete(id);
	}

}
