package com.spring.socialising.controllers;

import com.spring.socialising.components.SmsService;
import com.spring.socialising.entities.User;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.services.UserService.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/sign-up")
public class SignUpController {
    @Autowired
    private SmsService smsService;

    @Autowired
    private UserService userService;

    @ApiOperation("Send OTP to client for signup")
    @PostMapping("/verify-otp/{phonenumber}")
    public ResponseEntity<ResponseData> sendOTPSignUp(@Valid @PathVariable("phonenumber") String number){
        return new ResponseEntity<>(userService.veritifyOTPSignUp(number),HttpStatus.OK);
    }
}
