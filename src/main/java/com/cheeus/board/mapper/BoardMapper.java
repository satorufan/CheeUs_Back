package com.cheeus.board.mapper;

import com.cheeus.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
	Integer findLatest();
    List<BoardDto> findAll();
    List<BoardDto> findAllFreeboard();
    List<BoardDto> findAllShortform();
    List<BoardDto> findAllEventboard();
    BoardDto findById(int id);
    void insert(BoardDto board);
    void update(BoardDto board);
    void delete(int id);
    int getMaxIdFB();
    int getMaxIdEB();

    // 좋아요 관련 메서드
    void addLike(@Param("id") int id, @Param("userEmail") String userEmail);
    void removeLike(@Param("id") int id, @Param("userEmail") String userEmail);
    Boolean isLikedByUser(@Param("id") int id, @Param("userEmail") String userEmail);
    int getLikeCount(@Param("id") int id);
    void updateLikeCount(@Param("id") int id) ;

    // 조회수 관련 메서드
    void incrementViewCount(int id);
    int getViewCount(int id);

}
