package com.spring.socialising.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "delete-conversation")
public class DeleteConversation {
    @Id
    private String id;

    private String conversation_id;

    private String users_id;

    @CreatedDate
    private String created_at;
}
