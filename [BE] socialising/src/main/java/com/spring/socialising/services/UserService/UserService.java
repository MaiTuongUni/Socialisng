package com.spring.socialising.services.UserService;

import com.spring.socialising.entities.User;
import com.spring.socialising.entities.response.ResponseData;

public interface UserService {
    User findUserByPhoneNumber(String phoneNumber);

    ResponseData veritifyOTPSignUp(@Valid String number);
}
