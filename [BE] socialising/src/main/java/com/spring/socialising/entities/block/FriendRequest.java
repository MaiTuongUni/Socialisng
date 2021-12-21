package com.spring.socialising.entities.block;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {
    private String user_id;

    private String text;

    private LocalDateTime time_request;
}
