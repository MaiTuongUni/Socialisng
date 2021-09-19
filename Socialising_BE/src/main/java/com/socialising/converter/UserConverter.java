package com.socialising.converter;

import com.socialising.dto.CustomerDTO;
import com.socialising.dto.MyUser;
import com.socialising.dto.UserDTO;
import com.socialising.dto.UserInfoDTO;
import com.socialising.entity.RoleEntity;
import com.socialising.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO convertToDTO(UserEntity entity) {
		UserDTO dto = modelMapper.map(entity, UserDTO.class);
		List<RoleEntity> roles = entity.getRoles();
		roles.forEach(item -> {
			dto.setRoleCode(item.getCode());
		});
		return dto;
	}

	public UserDTO convertToDTO(MyUser myUser) {
		UserDTO dto = modelMapper.map(myUser, UserDTO.class);
		dto.setUserName(myUser.getUsername());
		return dto;
	}

	public UserInfoDTO convertToInfoDTO(UserEntity entity) {
		UserInfoDTO dto = modelMapper.map(entity, UserInfoDTO.class);
		return dto;
	}

	public UserInfoDTO convertToInfoDTO(User user) {
		UserInfoDTO dto = modelMapper.map(user, UserInfoDTO.class);
		return dto;
	}

	public CustomerDTO convertToCustomerDTO(UserEntity entity) {
		return modelMapper.map(entity, CustomerDTO.class);
	}

	public UserEntity convertToEntity(UserDTO dto) {
		UserEntity entity = modelMapper.map(dto, UserEntity.class);
		return entity;
	}

}
