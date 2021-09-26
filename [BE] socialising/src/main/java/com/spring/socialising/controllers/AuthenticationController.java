package com.spring.socialising.controllers;

import com.spring.socialising.components.SmsService;
import com.spring.socialising.dtos.AccountDTO;
import com.spring.socialising.dtos.TokenDetails;
import com.spring.socialising.entities.User;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.exceptions.InvalidException;
import com.spring.socialising.exceptions.UserNotFoundAuthenticationException;
import com.spring.socialising.securities.AccountDetailsService;
import com.spring.socialising.securities.JwtTokenUtils;
import com.spring.socialising.securities.JwtUserDetails;
import com.spring.socialising.securities.provider.AccountAuthenticationToken;
import com.spring.socialising.services.UserService.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Locale;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/rest/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountDetailsService accountDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private MessageSource messageSource;

    @ApiOperation("User login form (phone_number, password)")
    @PostMapping()
    public ResponseEntity<ResponseData> loginUser(@Valid @RequestBody AccountDTO dto){
        Locale locale = LocaleContextHolder.getLocale();
        AccountAuthenticationToken authenticationToken = new AccountAuthenticationToken(
                dto.getUsername(),
                dto.getPassword(),
                true
        );
        try{
            authenticationManager.authenticate(authenticationToken);
            final JwtUserDetails userDetails = accountDetailsService
                    .loadUserByUsername(dto.getUsername());

            final TokenDetails result = jwtTokenUtils.getTokenDetails(userDetails, null);

            return new ResponseEntity<>(ResponseData.builder()
                    .success(true)
                    .message(messageSource.getMessage("success.user-login", null,locale))
                    .data(result)
                    .build(),OK);
        }catch (UserNotFoundAuthenticationException | BadCredentialsException ex){
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message(ex.getMessage())
                    .data(dto)
                    .build(), BAD_REQUEST);
        }
    }
}
