package com.cheeus.user.event;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserEventMapper {
    List<UserEventDto> findAll();
    UserEventDto findById(int id);

    // 좋아요 관련 메서드
    void addLike(@Param("eventId") int eventId, @Param("memberEmail") String memberEmail);
    void removeLike(@Param("eventId") int eventId, @Param("memberEmail") String memberEmail);
    Boolean isLikedByUser(@Param("eventId") int eventId, @Param("memberEmail") String memberEmail);
    int getLikeCount(@Param("eventId") int eventId);
    void updateLikeCount(@Param("eventId") int eventId);

    void incrementViewCount(int id);
    int getViewCount(int id);

}
