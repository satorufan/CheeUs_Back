package com.cheeus.user.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEventServiceImpl implements UserEventService {

	@Autowired
	private UserEventMapper eventMapper;

	@Override
	public List<UserEventDto> findAll() {
		return eventMapper.findAll();
	}


	@Override
	public Optional<UserEventDto> findById(int id) {
		return Optional.ofNullable(eventMapper.findById(id));
	}

	@Override
	public Integer toggleLike(int eventId, String memberEmail) {
		Boolean isLiked = eventMapper.isLikedByUser(eventId, memberEmail);

		// null 체크 후 기본값 처리
		if (isLiked == null) {
			isLiked = false;
		}

		try {
			if (isLiked) {
				// 이미 좋아요를 눌렀다면 좋아요 취소 (레코드 삭제)
				eventMapper.removeLike(eventId, memberEmail);
			} else {
				// 좋아요를 누르지 않았다면 좋아요 추가
				eventMapper.addLike(eventId, memberEmail);
			}
			eventMapper.updateLikeCount(eventId);
			return eventMapper.getLikeCount(eventId);
		} catch (DuplicateKeyException e) {
			// 중복된 엔트리 삽입 시 발생하는 예외 처리
			System.out.println("중복된 좋아요 시도: " + e.getMessage());
			eventMapper.removeLike(eventId, memberEmail);
			eventMapper.updateLikeCount(eventId);
			return eventMapper.getLikeCount(eventId);
		}

	}

	@Override
	public Boolean isLikedByUser(int eventId, String memberEmail) {
		return eventMapper.isLikedByUser(eventId, memberEmail);
	}

	@Override
	public int incrementViewCount(int id) {
		eventMapper.incrementViewCount(id);
		return eventMapper.getViewCount(id);
	}

}
