package com.cheeus.user.magazine;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMagazineMapper {
    List<UserMagazineDto> findAll();
    UserMagazineDto findById(int id);

    // 좋아요 관련 메서드
    void addLike(@Param("magazineId") int magazineId, @Param("memberEmail") String memberEmail);
    void removeLike(@Param("magazineId") int magazineId, @Param("memberEmail") String memberEmail);
    Boolean isLikedByUser(@Param("magazineId") int magazineId, @Param("memberEmail") String memberEmail);
    int getLikeCount(@Param("magazineId") int magazineId);
    void updateLikeCount(@Param("magazineId") int magazineId);
}
