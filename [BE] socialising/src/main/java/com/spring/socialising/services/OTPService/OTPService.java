package com.spring.socialising.services.OTPService;

import com.spring.socialising.entities.OTP;

import java.text.ParseException;

public interface OTPService {
    void sendOTP( String number, int type) throws ParseException;

    boolean insertOrUpdateOTP(OTP otp,int type);

    OTP findByPhoneNumberAndType(String phonenumber, int type);

    boolean update(OTP otpFound);

    boolean checkTimeOutOTP(OTP otpFound);

    boolean removeOTP(OTP otpFound);
}
