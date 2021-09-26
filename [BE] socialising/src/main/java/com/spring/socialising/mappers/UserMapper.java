package com.spring.socialising.mappers;

import com.spring.socialising.dtos.UserDTO;
import com.spring.socialising.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    public User mapperToUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO,User.class);
        return user;
    }

    public UserDTO mapperToUserDTO(User user){
        UserDTO userDto = modelMapper.map(user,UserDTO.class);
        return userDto;
    }
}
