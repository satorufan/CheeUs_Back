package com.cheeus.board.service;

import com.cheeus.board.dto.DtBoardDto;
import com.cheeus.board.mapper.DtBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DtBoardServiceImpl implements DtBoardService {

	@Autowired
	private DtBoardMapper dtBoardMapper;

	@Override
	public List<DtBoardDto> findAll() {
		return dtBoardMapper.findAll();
	}

	@Override
	public Optional<DtBoardDto> findById(int id) {
		return Optional.ofNullable(dtBoardMapper.findById(id));
	}

	@Override
	public void insert(DtBoardDto board) {
		dtBoardMapper.insert(board);
	}
	
	@Override
	public int responseForInsert() {
		return dtBoardMapper.responseForInsert();
	}

	@Override
	public void update(DtBoardDto board) {
		dtBoardMapper.update(board);
	}

	@Override
	public void delete(int id) {
		dtBoardMapper.delete(id);
	}
}
