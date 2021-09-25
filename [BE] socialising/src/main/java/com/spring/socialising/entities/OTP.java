package com.spring.socialising.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "verification-code")
public class OTP {
    @Id
    private UUID id;

    @JsonIgnore
    private int otp;

    private String phoneNumber;

    @JsonIgnore
    Instant createDate;
}
