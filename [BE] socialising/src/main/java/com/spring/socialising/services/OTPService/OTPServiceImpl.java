package com.spring.socialising.services.OTPService;

import com.spring.socialising.components.SmsService;
import com.spring.socialising.entities.OTP;
import com.spring.socialising.repositories.OTPRepository.OTPRepository;
import com.spring.socialising.utils.GeneralFuntion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class OTPServiceImpl implements OTPService {

    private final SmsService smsService;

    private final OTPRepository otpRepository;

    @Value(value="${otp.ExpriedTime}")
    private long ExpriedTime;

    public OTPServiceImpl(SmsService smsService, OTPRepository otpRepository) {
        this.smsService = smsService;
        this.otpRepository = otpRepository;
    }

    @Override
    public void sendOTP(String number) throws ParseException {
        OTP otp = new OTP();
        //Generate OTP
        otp.setOtp(smsService.generateOTP());

        //Send OTP
        //smsService.sendMessageOTP(number,otp.getOtp());

        //Insert or Update OTP
        otp.setPhoneNumber(number);
        this.insertOrUpdateOTP(otp, 0);
    }

    @Override
    public boolean insertOrUpdateOTP(OTP otp, int type) {
        OTP otpFound = otpRepository.findByPhoneNumber(otp.getPhoneNumber());

        if(otpFound == null){ //OTP for new
            otp.setCreateDate(LocalDateTime.now());
            otp.setActive(false);
            otp.setType(type);
            otpRepository.save(otp);
        }
        else
        {  //OTP was exist
            otpFound.setOtp(otp.getOtp());
            otpFound.setCreateDate(LocalDateTime.now());
            otpFound.setActive(false);
            otpRepository.save(otpFound);
        }
        return true;
    }

    @Override
    public OTP findByPhoneNumberAndType(String phonenumber, int type) {
        return otpRepository.findByPhoneNumberAndType(phonenumber, type);
    }

    @Override
    public boolean update(OTP otpFound) {
        return (otpRepository.save(otpFound) != null);
    }

    @Override
    public boolean checkTimeOutOTP(OTP otpFound) {
        return GeneralFuntion.isTimeOutOTP(otpFound.getCreateDate(),ExpriedTime);
    }

    @Override
    public boolean removeOTP(OTP otpFound) {
        otpRepository.delete(otpFound);
        return true;
    }
}
