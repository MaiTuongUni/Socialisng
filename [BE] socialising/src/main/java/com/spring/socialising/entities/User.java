package com.spring.socialising.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    private UUID id;

    private String phoneNumber;

    @Email
    private String email;

    private String firstName;

    private String lastName;

    private String middleName;

    private Boolean isActive;

    @CreatedDate
    private Instant createDate;
}
