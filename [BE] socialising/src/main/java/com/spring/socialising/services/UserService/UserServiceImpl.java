package com.spring.socialising.services.UserService;

import com.spring.socialising.entities.User;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.repositories.UserRepository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public ResponseData veritifyOTPSignUp(@Valid String number) {
        return null;
    }
}
