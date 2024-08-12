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
    void delete(int id);

    // 좋아요 관련 메서드
    void addLike(@Param("id") int postId, @Param("authorId") String userId);
    void removeLike(@Param("id") int postId, @Param("authorId") String userId);
    Boolean isLikedByUser(@Param("id") int postId, @Param("authorId") String userId);
    int getLikeCount(@Param("id") int id);
    void updateLikeCount(@Param("id") int postId);

    // 조회수 관련 메서드
    void incrementViewCount(int id);
    int getViewCount(int id);
}
