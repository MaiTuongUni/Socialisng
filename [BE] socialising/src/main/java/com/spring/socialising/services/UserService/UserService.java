package com.spring.socialising.services.UserService;

import com.spring.socialising.dtos.PasswordDTO;
import com.spring.socialising.dtos.UserDTO;
import com.spring.socialising.entities.User;
import com.spring.socialising.securities.JwtUserDetails;

public interface UserService {
    User findUserByPhoneNumber(String phoneNumber);

    boolean createUser(UserDTO userDTO);

    boolean changePasswordByOTP(String userName, String newPassword);

    boolean changePasswordByLogin(JwtUserDetails userDetails, PasswordDTO passwordDTO);

    boolean updateUser(User user);
}
