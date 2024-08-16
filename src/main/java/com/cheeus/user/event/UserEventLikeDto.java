package com.cheeus.user.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEventLikeDto {

    // EventLike DB
    private int id;
    @JsonProperty("eventId")
    private int eventId;
    @JsonProperty("memberEmail")
    private String memberEmail;
}
