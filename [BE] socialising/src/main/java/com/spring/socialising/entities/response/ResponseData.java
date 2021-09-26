package com.spring.socialising.entities.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseData {
    private boolean success;

    private String message;

    private Object data;
}
