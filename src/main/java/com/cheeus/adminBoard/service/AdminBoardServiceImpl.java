package com.cheeus.adminBoard.service;

import com.cheeus.adminBoard.dto.AdminBoardDto;
import com.cheeus.adminBoard.mapper.AdminBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminBoardServiceImpl implements AdminBoardService {

	@Autowired
	private AdminBoardMapper boardMapper;

	@Override
	public List<AdminBoardDto> findAll() {
		return boardMapper.findAll();
	}

	@Override
	public List<AdminBoardDto> findAllFreeboard() {
		return boardMapper.findAllFreeboard();
	}

	@Override
	public List<AdminBoardDto> findAllShortform() {
		return boardMapper.findAllShortform();
	}

	@Override
	public List<AdminBoardDto> findAllEventboard() {
		return boardMapper.findAllEventboard();
	}

	@Override
	public Optional<AdminBoardDto> findById(int id) {
		return Optional.ofNullable(boardMapper.findById(id));
	}

	@Override
	public void insert(AdminBoardDto board) {
		boardMapper.insert(board);
	}


	@Override
	public void update(AdminBoardDto board) {
		boardMapper.update(board);
	}

	@Override
	public void delete(int id) {
		boardMapper.delete(id);
	}

}
