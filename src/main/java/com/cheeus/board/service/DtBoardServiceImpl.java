package com.cheeus.board.service;

import com.cheeus.board.dto.DtBoardDto;
import com.cheeus.board.mapper.DtBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
	public Integer toggleLike(int postId, String userId) {
		Boolean isLiked = dtBoardMapper.isLikedByUser(postId, userId);

		// null 체크 후 기본값 처리
		if (isLiked == null) {
			isLiked = false;
		}

		try {
			if (isLiked) {
				// 이미 좋아요를 눌렀다면 좋아요 취소 (레코드 삭제)
				dtBoardMapper.removeLike(postId, userId);
			} else {
				// 좋아요를 누르지 않았다면 좋아요 추가
				dtBoardMapper.addLike(postId, userId);
			}
			dtBoardMapper.updateLikeCount(postId);
			return dtBoardMapper.getLikeCount(postId);
		} catch (DuplicateKeyException e) {
			// 중복된 엔트리 삽입 시 발생하는 예외 처리
			System.out.println("중복된 좋아요 시도: " + e.getMessage());
			dtBoardMapper.removeLike(postId, userId);
			dtBoardMapper.updateLikeCount(postId);
			return dtBoardMapper.getLikeCount(postId);
		}

	}

	@Override
	public Boolean isLikedByUser(int postId, String userId) {
		return dtBoardMapper.isLikedByUser(postId, userId);
	}

	@Override
	public void delete(int id) {
		dtBoardMapper.delete(id);
	}

	@Override
	public int incrementViewCount(int id) {
		dtBoardMapper.incrementViewCount(id);
		return dtBoardMapper.getViewCount(id);
	}
	
	
}
