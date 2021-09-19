package com.socialising.service;

import com.socialising.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO create(UserDTO user);
    CustomerDTO register(UserDTO user) throws Exception;
    UserDTO update(Long id, UserDTO user);
    void delete(long[] ids);
    SearchDTO<UserDTO> search(UserDTO userDTO, Pageable pageable);
    UserInfoDTO findByUsername(String username);
    UserDTO getProfile();

    UserDTO normalUserUpdate(String fullName, String email, String phone, String address);
}
