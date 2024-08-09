package com.cheeus.user.event;

import org.springframework.beans.factory.annotation.Autowired;
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

}
