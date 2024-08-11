package com.cheeus.board.mapper;

import com.cheeus.board.dto.DtBoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DtBoardMapper {
    List<DtBoardDto> findAll();
    DtBoardDto findById(int id);
    void insert(DtBoardDto board);
    int responseForInsert();
    void update(DtBoardDto board);
    boolean isPostLiked(int id);
    void increaseLike(int id);
    void decreaseLike(int id);
    // int getLikeCount(int id);
    // void toggleLikeOn(int id);
    // void toggleLikeOff(int id);
    void delete(int id);

    // 좋아요 관련 메서드
    void addLike(@Param("id") int postId, @Param("authorId") String userId);
    void removeLike(@Param("id") int postId, @Param("authorId") String userId);
    Boolean isLikedByUser(@Param("id") int postId, @Param("authorId") String userId);
    Integer getLikeCount(@Param("id") int postId);
    void updateLikeCount(@Param("id") int postId);

}
