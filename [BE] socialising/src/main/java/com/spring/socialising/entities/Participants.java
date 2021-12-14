package com.spring.socialising.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "participants")
public class Participants {
    @Id
    private String id;

    private String conversation_id;

    private String users_id;

    @CreatedDate
    private DateTime created_at;

    @LastModifiedDate
    private DateTime updated_at;
}
