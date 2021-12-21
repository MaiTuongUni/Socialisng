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
import java.util.ArrayList;
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

    @JsonIgnore
    private List<FriendApprove> friend = new ArrayList<>();

    @JsonIgnore
    private List<FriendRequest> friend_request = new ArrayList<>();

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createDate;

    @JsonIgnore
    @LastModifiedDate
    private  LocalDateTime updatedDate;

    public void addFriendRequest(FriendRequest friendRequest){
        friendRequest.setTime_request(LocalDateTime.now());

        FriendApprove friendApprove = new FriendApprove();
        friendApprove.setUser_id(friendRequest.getUser_id());
        this.removeFriendRequest(friendApprove);
        this.friend_request.add(friendRequest);
    }

    public void removeFriendRequest(FriendApprove friendRequest){
        for (FriendRequest request: this.friend_request) {
            if(request.getUser_id().equals(friendRequest.getUser_id())){
                this.friend_request.remove(request);
            }
        }
    }

    public void addFriend(FriendApprove friendApprove){
        friendApprove.setCreated_date(LocalDateTime.now());
        this.friend.add(friendApprove);
    }

    public void removeFriend(FriendApprove friendApprove){
        for (FriendApprove friendItem: this.friend) {
            if(friendItem.getUser_id().equals(friendApprove.getUser_id())){
                this.friend.remove(friendItem);
            }
        }
    }
}
