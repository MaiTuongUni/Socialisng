package com.spring.socialising.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OTP {
    int otp;

    String phoneNumber;

    DateTime dateTime;
}
