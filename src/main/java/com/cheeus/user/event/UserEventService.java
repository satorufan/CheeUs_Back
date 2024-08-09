package com.cheeus.user.event;

import java.util.List;
import java.util.Optional;

public interface UserEventService {
    List<UserEventDto> findAll();
    Optional<UserEventDto> findById(int id);
}
