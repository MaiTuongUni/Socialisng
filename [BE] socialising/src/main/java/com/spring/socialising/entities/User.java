package com.spring.socialising.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.socialising.entities.block.FriendApprove;
import com.spring.socialising.entities.block.FriendRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String phoneNumber;

    @Email
    private String email;

    private String fullName;

    private String dateOfBirth;

    private String code_register_socket;

    private String urlImage;

    @JsonIgnore
    private String image_public_id;

    private String nick_name;

    private List<FriendApprove> friend_id;

    private List<FriendRequest> friend_request;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createDate;

    @JsonIgnore
    @LastModifiedDate
    private  LocalDateTime updatedDate;
}
