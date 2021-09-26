package com.spring.socialising.services.UserService;

import com.spring.socialising.dtos.UserDTO;
import com.spring.socialising.entities.User;

public interface UserService {
    User findUserByPhoneNumber(String phoneNumber);

    User createBatchUser(String number);

    boolean createUser(UserDTO userDTO);
}
