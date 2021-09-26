package com.spring.socialising.services.UserService;

import com.spring.socialising.dtos.UserDTO;
import com.spring.socialising.entities.Account;
import com.spring.socialising.entities.User;
import com.spring.socialising.mappers.UserMapper;
import com.spring.socialising.repositories.AccountRepository.AccountRepository;
import com.spring.socialising.repositories.UserRepository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, AccountRepository accountRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User createBatchUser(String number) {
        User userBatch = new User();
        userBatch.setPhoneNumber(number);

        userRepository.save(userBatch);

        return userBatch;
    }

    @Override
    @Transactional
    public boolean createUser(UserDTO userDTO) {
        User user = userMapper.mapperToUser(userDTO);
        userRepository.save(user);

        //create account for user
        Account account = new Account();
        account.setUserName(userDTO.getPhoneNumber());
        account.setPassword(userDTO.getPassword());
        account.setRoles(new ArrayList<>(Collections.singleton("USER")));
        accountRepository.save(account);
        return true;
    }

}
