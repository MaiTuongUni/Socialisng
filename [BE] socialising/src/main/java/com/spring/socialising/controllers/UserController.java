package com.spring.socialising.controllers;

import com.spring.socialising.dtos.OTPDTO;
import com.spring.socialising.dtos.PasswordDTO;
import com.spring.socialising.entities.OTP;
import com.spring.socialising.entities.User;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.securities.JwtUserDetails;
import com.spring.socialising.services.OTPService.OTPService;
import com.spring.socialising.services.UserService.UserService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    private final UserService userService;

    private final OTPService otpService;

    private final MessageSource messageSource;
    private ResponseEntity<ResponseData> responseDataResponseEntity;

    public UserController(UserService userService, OTPService otpService, MessageSource messageSource) {
        this.userService = userService;
        this.otpService = otpService;
        this.messageSource = messageSource;
    }

    @GetMapping("/information")
    public ResponseEntity<ResponseData> getInformationUser(){
        Locale locale = LocaleContextHolder.getLocale();
        try{
            JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.findUserByPhoneNumber(userDetails.getUsername());
            return new ResponseEntity<>(ResponseData.builder()
                    .success(true)
                    .message(messageSource.getMessage("success.user-information", null,locale))
                    .data(user)
                    .build(), OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message(ex.getMessage())
                    .data(null)
                    .build(), BAD_REQUEST);
        }
    }

    @GetMapping("/change-password")
    public ResponseEntity<ResponseData> getInformationUser(@Valid @RequestBody PasswordDTO passwordDTO){
        Locale locale = LocaleContextHolder.getLocale();
        try{
            JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           if(userService.changePasswordByLogin(userDetails, passwordDTO)){
               return new ResponseEntity<>(ResponseData.builder()
                       .success(true)
                       .message(messageSource.getMessage("success.user-password-changed", null,locale))
                       .data(null)
                       .build(), OK);
           }
           return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message(messageSource.getMessage("error.user.wrong-password", null,locale))
                    .data(null)
                    .build(), BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message(ex.getMessage())
                    .data(null)
                    .build(), BAD_REQUEST);
        }
    }

    @GetMapping("/common/forgot-password/{phonenumber}")
    public ResponseEntity<ResponseData> sendOTPForgotPassword(@Valid @PathVariable("phonenumber") String number){
        Locale locale = LocaleContextHolder.getLocale();
        try{
            User user = userService.findUserByPhoneNumber(number);

            //Check account exist
            if(user == null){
                return new ResponseEntity<>(ResponseData.builder()
                        .success(false)
                        .message(messageSource.getMessage("error.user.invalid-verify-otp", null,locale))
                        .data(number)
                        .build(), BAD_REQUEST);
            }

            otpService.sendOTP(number,1);
            return new ResponseEntity<>(ResponseData.builder()
                    .success(true)
                    .message(messageSource.getMessage("success.otp-sent", null,locale))
                    .data(number)
                    .build(), OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message(ex.getMessage())
                    .data(null)
                    .build(), BAD_REQUEST);
        }
    }

    @PostMapping("/common/active-otp-change-password/{phonenumber}")
    public ResponseEntity<ResponseData> activeOTPChangePassword(@Valid @PathVariable("phonenumber") String phonenumber, @RequestBody OTPDTO otp){
        Locale locale = LocaleContextHolder.getLocale();
        try{
            OTP otpFound = otpService.findByPhoneNumberAndType(phonenumber,1);
            //Check account exist
            if(otpFound != null){
                if(otpFound.getOtp() == otp.getOtp() && !otpFound.isActive() && otpService.checkTimeOutOTP(otpFound)) {
                    //Cập nhật OTP
                    otpFound.setActive(true);
                    otpService.update(otpFound);
                    return new ResponseEntity<>(ResponseData.builder()
                            .success(true)
                            .message(messageSource.getMessage("success.otp-verified", null,locale))
                            .data(otp)
                            .build(), OK);
                }
            }
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message(messageSource.getMessage("error.user.invalid-verify-otp", null,locale))
                    .data(phonenumber)
                    .build(), BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message(ex.getMessage())
                    .data(null)
                    .build(), BAD_REQUEST);
        }
    }

    @GetMapping("/common/change-password-by-otp/{phonenumber}")
    public ResponseEntity<ResponseData> changePasswordByOTP(@Valid @PathVariable("phonenumber") String number,@Valid @RequestBody PasswordDTO passwordDTO){
        Locale locale = LocaleContextHolder.getLocale();
        try{
            if(userService.changePasswordByOTP(number, passwordDTO.getNewPassword())){
                return new ResponseEntity<>(ResponseData.builder()
                        .success(true)
                        .message(messageSource.getMessage("success.user-password-changed", null,locale))
                        .data(number)
                        .build(), OK);
            }
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message(messageSource.getMessage("error.user.invalid-verify-otp", null,locale))
                    .data(number)
                    .build(), BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message(ex.getMessage())
                    .data(null)
                    .build(), BAD_REQUEST);
        }
    }
}
