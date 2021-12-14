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
@Document(collection = "access")
public class Access {
    @Id
    private String id;

    private String users_id;

    private String devices_id;

    private String token;

    @CreatedDate
    private DateTime created_at;

    @LastModifiedDate
    private DateTime updated_at;
}
