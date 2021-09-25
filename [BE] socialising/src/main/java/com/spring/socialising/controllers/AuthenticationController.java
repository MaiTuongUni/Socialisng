package com.spring.socialising.controllers;

import com.spring.socialising.components.SmsService;
import com.spring.socialising.dtos.AccountDto;
import com.spring.socialising.dtos.TokenDetails;
import com.spring.socialising.entities.User;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.exceptions.InvalidException;
import com.spring.socialising.exceptions.UserNotFoundAuthenticationException;
import com.spring.socialising.securities.provider.AccountAuthenticationToken;
import com.spring.socialising.services.UserService.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
