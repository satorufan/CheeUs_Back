package com.cheeus.user.magazine;

import java.util.List;
import java.util.Optional;

public interface UserMagazineService {
    List<UserMagazineDto> findAll();
    Optional<UserMagazineDto> findById(int id);

    Integer toggleLike(int magazineId, String memberEmail);
    Boolean isLikedByUser(int magazineId, String memberEmail);

    int incrementViewCount(int id);
}
