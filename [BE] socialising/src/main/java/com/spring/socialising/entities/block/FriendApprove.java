package com.spring.socialising.entities.block;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendApprove {
    private String user_id;

    @CreatedDate
    private String created_date;
}
