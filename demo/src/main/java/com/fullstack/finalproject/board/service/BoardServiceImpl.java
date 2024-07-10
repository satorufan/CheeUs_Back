package com.fullstack.finalproject.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.finalproject.board.dto.BoardDto;
import com.fullstack.finalproject.board.mapper.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDto> findAll() {
		return boardMapper.findAll();
	}


	@Override
	public Optional<BoardDto> findById(int board_no) {
		return boardMapper.findById(board_no);
	}
	@Override
	public Optional<BoardDto> findByIdReview(int board_no) {
		return boardMapper.findByIdReview(board_no);
	}
	@Override
	public List<BoardDto> findByIdRepls(int board_no) {
		return boardMapper.findByIdRepls(board_no);
	}

	
	
	@Override
	public void recruitInsert(BoardDto board) {
		boardMapper.insert(board);
		boardMapper.recruitInsert(board);
	}
	@Override
	public void reviewInsert(BoardDto board) {
		boardMapper.insert(board);
		boardMapper.reviewInsert(board);
	}
	@Override
	public void insertRepl(BoardDto board) {
		boardMapper.insertRepl(board);
	}

	
	@Override
	public void recruitUpdate(BoardDto board) {
		boardMapper.update(board);
		boardMapper.recruitUpdate(board);
	}

	
	
	@Override
	public void delete(int board_no) {
		System.out.println(board_no);
		boardMapper.delete(board_no);
	}
}
