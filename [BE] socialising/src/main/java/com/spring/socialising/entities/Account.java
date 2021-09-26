package com.spring.socialising.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    @Id
    private String id;

    private String userName;

    @JsonIgnore
    private String password;

    private List<String> roles = new ArrayList<>();

    private boolean active = true;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
