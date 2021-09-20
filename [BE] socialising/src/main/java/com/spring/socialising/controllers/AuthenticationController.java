package com.spring.socialising.controllers;

import com.spring.socialising.components.SmsService;
import com.spring.socialising.dtos.AccountDto;
import com.spring.socialising.dtos.TokenDetails;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.exceptions.InvalidException;
import com.spring.socialising.exceptions.UserNotFoundAuthenticationException;
import com.spring.socialising.securities.provider.AccountAuthenticationToken;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SmsService smsService;


    @ApiOperation("Send OTP to client for signup")
    @GetMapping("/send-otp/{phonenumber}")
    public  ResponseEntity<ResponseData> sendOTPSignUp(@Valid @PathVariable("phonenumber") String number){
        ResponseData responseData = new ResponseData();
        try{
            boolean sendFlag = smsService.sendMessageOTP(number);
            if(!sendFlag){
                responseData.setSuccess(false);
                responseData.setMessage("faild");
                responseData.setData(number);
                return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            responseData.setData(number);
            responseData.setSuccess(true);
            responseData.setMessage("passed");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }catch (Exception ex){
            responseData.setSuccess(false);
            responseData.setMessage(ex.getMessage());
            responseData.setData(number);
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("User login form (phone_number, password)")
    @PostMapping()
    public ResponseEntity<TokenDetails> loginUser(@Valid @RequestBody AccountDto dto){
        AccountAuthenticationToken authenticationToken = new AccountAuthenticationToken(
                dto.getUsername(),
                dto.getPassword(),
                true
        );

        try{

        }catch (UserNotFoundAuthenticationException | BadCredentialsException ex){
            throw new InvalidException(ex.getMessage());
        }

        return null;
    }
}
