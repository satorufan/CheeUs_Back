package com.cheeus.user.magazine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserMagazineLikeDto {

    // EventLike DB
    private int id;
    @JsonProperty("magazine_id")
    private int magazineId;
    @JsonProperty("memberEmail")
    private String memberEmail;
}
