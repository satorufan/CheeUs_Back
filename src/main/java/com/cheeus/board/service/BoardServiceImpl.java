package com.fullstack.sfinal.cheeusbackend.board.service;

import com.fullstack.finalproject.board.dto.BoardDto;
import com.fullstack.finalproject.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDto> findAll() {
		return boardMapper.findAll();
	}

	@Override
	public Optional<BoardDto> findById(int id) {
		return Optional.ofNullable(boardMapper.findById(id));
	}

	@Override
	public void insert(BoardDto board) {
		boardMapper.insert(board);
	}

	@Override
	public void update(BoardDto board) {
		boardMapper.update(board);
	}

	@Override
	public void delete(int id) {
		boardMapper.delete(id);
	}
}
