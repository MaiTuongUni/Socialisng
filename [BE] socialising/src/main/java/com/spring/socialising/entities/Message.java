package com.spring.socialising.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "message")
public class Message {
    @Id
    private String id;

    private String conversation;

    private String sender_id;

    private String message_type;

    private String message;

    @CreatedDate
    private DateTime created_at;

}
