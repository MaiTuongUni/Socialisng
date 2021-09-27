package com.spring.socialising.services.UserService;

import com.spring.socialising.dtos.PasswordDTO;
import com.spring.socialising.dtos.UserDTO;
import com.spring.socialising.entities.Account;
import com.spring.socialising.entities.OTP;
import com.spring.socialising.entities.User;
import com.spring.socialising.mappers.UserMapper;
import com.spring.socialising.repositories.AccountRepository.AccountRepository;
import com.spring.socialising.repositories.OTPRepository.OTPRepository;
import com.spring.socialising.repositories.UserRepository.UserRepository;
import com.spring.socialising.securities.JwtUserDetails;
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

    private final OTPRepository otpRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, AccountRepository accountRepository, OTPRepository otpRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.otpRepository = otpRepository;
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
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

    @Override
    @Transactional
    public boolean changePasswordByOTP(String userName, String newPassword) {
        if(newPassword == null) return false;
        if("".equals(newPassword)) return false;

        OTP otp = otpRepository.findByPhoneNumberAndType(userName,1);
        if(otp != null){
            if(otp.isActive()){
                Account account = accountRepository.findByUserName(userName);
                account.setPassword(newPassword);
                accountRepository.save(account);

                otpRepository.delete(otp);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changePasswordByLogin(JwtUserDetails userDetails, PasswordDTO passwordDTO) {
        if(userDetails.getPassword().equals(passwordDTO.getOldPassword()) && passwordDTO.getNewPassword() != ""){
            Account account = accountRepository.findByUserName(userDetails.getUsername());
            account.setPassword(passwordDTO.getNewPassword());

            accountRepository.save(account);
            return true;
        }
        return false;
    }

}
