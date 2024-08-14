package com.cheeus.user.magazine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserMagazineServiceImpl implements UserMagazineService {

	@Autowired
	private UserMagazineMapper magazineMapper;

	@Override
	public List<UserMagazineDto> findAll() {
		return magazineMapper.findAll();
	}

	@Override
	public Optional<UserMagazineDto> findById(int id) {
		return Optional.ofNullable(magazineMapper.findById(id));
	}

	@Override
	public Integer toggleLike(int magazineId, String memberEmail) {
		Boolean isLiked = magazineMapper.isLikedByUser(magazineId, memberEmail);

		// null 체크 후 기본값 처리
		if (isLiked == null) {
			isLiked = false;
		}

		try {
			if (isLiked) {
				// 이미 좋아요를 눌렀다면 좋아요 취소 (레코드 삭제)
				magazineMapper.removeLike(magazineId, memberEmail);
			} else {
				// 좋아요를 누르지 않았다면 좋아요 추가
				magazineMapper.addLike(magazineId, memberEmail);
			}
			magazineMapper.updateLikeCount(magazineId);
			return magazineMapper.getLikeCount(magazineId);
		} catch (DuplicateKeyException e) {
			// 중복된 엔트리 삽입 시 발생하는 예외 처리
			System.out.println("중복된 좋아요 시도: " + e.getMessage());
			magazineMapper.removeLike(magazineId, memberEmail);
			magazineMapper.updateLikeCount(magazineId);
			return magazineMapper.getLikeCount(magazineId);
		}

	}

	@Override
	public Boolean isLikedByUser(int eventId, String memberEmail) {
		return magazineMapper.isLikedByUser(eventId, memberEmail);
	}

	@Override
	public int incrementViewCount(int id) {
		magazineMapper.incrementViewCount(id);
		return magazineMapper.getViewCount(id);
	}

}
