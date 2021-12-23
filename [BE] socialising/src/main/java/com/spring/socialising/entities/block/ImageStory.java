package com.spring.socialising.entities.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class ImageStory {
    @CreatedDate
    private LocalDateTime createTime;

    private String url;

    private String url_public_id;
}
