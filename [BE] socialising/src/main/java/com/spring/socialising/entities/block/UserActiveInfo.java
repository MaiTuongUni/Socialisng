package com.spring.socialising.entities.block;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserActiveInfo {
    private String userId;

    private boolean isActive;

    private LocalDateTime time;

    private String socketAddress;
}
