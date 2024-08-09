package com.cheeus.user.event;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserEventMapper {
    List<UserEventDto> findAll();
    UserEventDto findById(int id);
}
