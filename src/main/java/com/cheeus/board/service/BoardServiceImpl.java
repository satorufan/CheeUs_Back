package com.cheeus.board.service;

import com.cheeus.board.dto.BoardDto;
import com.cheeus.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public Integer findLatest() {
		return boardMapper.findLatest();
	}

	@Override
	public List<BoardDto> findAll() {
		return boardMapper.findAll();
	}

	@Override
	public List<BoardDto> findAllFreeboard() {
		return boardMapper.findAllFreeboard();
	}

	@Override
	public List<BoardDto> findAllShortform() {
		return boardMapper.findAllShortform();
	}

	@Override
	public List<BoardDto> findAllEventboard() {
		return boardMapper.findAllEventboard();
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
