package com.socialising.service.impl;

import com.socialising.constant.SystemConstant;
import com.socialising.converter.UserConverter;
import com.socialising.dto.*;
import com.socialising.entity.RoleEntity;
import com.socialising.entity.UserEntity;
import com.socialising.repository.RoleRepository;
import com.socialising.repository.UserRepository;
import com.socialising.service.IUserService;
import com.socialising.util.SecurityUtils;
import com.socialising.util.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(item -> userConverter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return userConverter.convertToDTO(userRepository.findById(id).get());
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO newUser) {
        RoleEntity role = roleRepository.findOneByCode(newUser.getRoleCode());
        UserEntity userEntity = userConverter.convertToEntity(newUser);
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setStatus(1);
        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));

        String bodySent = "Tài khoản của bạn đã được kích hoạt tại StarShop! "
                +"Tên đăng nhập: "+userEntity.getUserName()+""
                +"<Mật khẩu: "+SystemConstant.PASSWORD_DEFAULT+ ""
                +"StarShop xin cảm ơn!";
        sendEmailService.sendEmail(userEntity.getEmail(),bodySent,"Tài khoản được kích hoạt tại StarShop");
        return userConverter.convertToDTO(userRepository.save(userEntity));
    }

    @Override
    public CustomerDTO register(UserDTO newUser) throws Exception {
        CustomerDTO res = new CustomerDTO();
        if (userRepository.findOneByEmail(newUser.getEmail()) != null) {
            throw new Exception("Exist email");
        }
        RoleEntity role = roleRepository.findOneByCode(SystemConstant.ROLE_CUSTOMER);
        UserEntity userEntity = userConverter.convertToEntity(newUser);
        userEntity.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setStatus(SystemConstant.ACTIVE_STATUS);
        userConverter.convertToCustomerDTO(userRepository.save(userEntity));
        return res;
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO user) {
        RoleEntity role = roleRepository.findOneByCode(user.getRoleCode());
        UserEntity oldUser = userRepository.findById(id).get();
        UserEntity userEntity = userConverter.convertToEntity(user);
        userEntity.setId(id);
        userEntity.setUserName(oldUser.getUserName());
        userEntity.setStatus(user.getStatus());
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setPassword(oldUser.getPassword());
        UserDTO userDTO = userConverter.convertToDTO(userRepository.save(userEntity));
        return userDTO;
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (Long item : ids) {
            UserEntity userEntity = userRepository.findById(item).get();
            userEntity.setStatus(0);
            userRepository.save(userEntity);
        }
    }

    @Override
    public SearchDTO<UserDTO> search(UserDTO userDTO, Pageable pageable) {
        return null;
    }

    @Override
    public UserInfoDTO findByUsername(String username) {
        return userConverter.convertToInfoDTO(userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS));
    }

    @Override
    public UserDTO getProfile() {
    
        UserDTO userDTO = userConverter.convertToDTO(SecurityUtils.getPrincipal());
        List<RoleDTO> roleDTOS = new ArrayList<>();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setAuthority("ROLE_" + userRepository.findById(SecurityUtils.getPrincipal().getId()).get().getRoles().get(0).getCode() + "");
        roleDTOS.add(roleDTO);
        userDTO.setAuthorities(roleDTOS);
        userDTO.setPassword("");
        return userDTO;
    }

    @Override
    public UserDTO normalUserUpdate(String fullName, String email, String phone, String address) {
        UserDTO dataLogin = userConverter.convertToDTO(SecurityUtils.getPrincipal());
        UserEntity userLogin =  userRepository.findById(dataLogin.getId()).get();
        if(userLogin==null)
        {
            return null;
        }
        userLogin.setFullName(fullName);
        userLogin.setEmail(email);
        userLogin.setPhone(phone);
        userLogin.setAddress(address);
        UserDTO userDTO = userConverter.convertToDTO(userRepository.save(userLogin));
        return userDTO;

    }

    public boolean authorize(UserDTO loginVM) {
        boolean flat=false;
        if(loginVM.getPassword() !=null) {
            UserEntity user = userRepository.findOneByPassword(loginVM.getUserName());
            flat = passwordEncoder.matches(loginVM.getPassword(), user.getPassword());
        }else{
            return false;
        }
        return flat;
    }
    public UserDTO changepass(Long id,UserDTO user) {
        UserEntity oldUser = userRepository.findById(id).get();
        UserEntity userEntity = userConverter.convertToEntity(user);
        userEntity.setId(id);
        userEntity.setUserName(oldUser.getUserName());
        userEntity.setFullName(oldUser.getFullName());
        userEntity.setStatus(oldUser.getStatus());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        UserDTO userDTO = userConverter.convertToDTO(userRepository.save(userEntity));
        return userDTO;
    }
}
