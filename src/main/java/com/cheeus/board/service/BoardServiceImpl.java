package com.cheeus.board.service;

import com.cheeus.board.dto.BoardDto;
import com.cheeus.board.mapper.BoardMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

	@Override
	public int getMaxIdFB() {
		return boardMapper.getMaxIdFB();
	}
	
	@Override
	public int getMaxIdEB() {
		return boardMapper.getMaxIdEB();
	}

	@Override
	public Integer toggleLike(@Param("id") int id, @Param("userEmail") String userEmail) {
		Boolean isLiked = boardMapper.isLikedByUser(id, userEmail);

		// null 체크 후 기본값 처리
		if (isLiked == null) {
			isLiked = false;
		}

		try {
			if (isLiked) {
				// 이미 좋아요를 눌렀다면 좋아요 취소 (레코드 삭제)
				boardMapper.removeLike(id, userEmail);
			} else {
				// 좋아요를 누르지 않았다면 좋아요 추가
				boardMapper.addLike(id, userEmail);
			}
			boardMapper.updateLikeCount(id);
			return boardMapper.getLikeCount(id);
		} catch (DuplicateKeyException e) {
			// 중복된 엔트리 삽입 시 발생하는 예외 처리
			System.out.println("중복된 좋아요 시도: " + e.getMessage());
			boardMapper.removeLike(id, userEmail);
			boardMapper.updateLikeCount(id);
			return boardMapper.getLikeCount(id);
		}

	}

	@Override
	public Boolean isLikedByUser(@Param("id") int id, @Param("userEmail") String userEmail) {
		return boardMapper.isLikedByUser(id, userEmail);
	}

	@Override
	public int incrementViewCount(int id) {
		boardMapper.incrementViewCount(id);
		return boardMapper.getViewCount(id);
	}
	
}
