package com.cheeus.admin.adminPosts.service;

import com.cheeus.admin.adminPosts.dto.AdminPostDto;
import com.cheeus.admin.adminPosts.mapper.AdminPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminPostServiceImpl implements AdminPostService {

	@Autowired
	private AdminPostMapper boardMapper;

	@Override
	public List<AdminPostDto> findAll() {
		return boardMapper.findAll();
	}

	@Override
	public Optional<AdminPostDto> findById(int id) {
		return Optional.ofNullable(boardMapper.findById(id));
	}

	@Override
	public void insert(AdminPostDto board) {
		boardMapper.insert(board);
	}


	@Override
	public void update(AdminPostDto board) {
		boardMapper.update(board);
	}

	@Override
	public void delete(int id) {
		boardMapper.delete(id);
	}

}
