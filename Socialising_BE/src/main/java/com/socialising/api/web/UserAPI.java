package com.socialising.api.web;

import com.socialising.config.TokenProvider;
import com.socialising.converter.UserConverter;
import com.socialising.dto.AuthToken;
import com.socialising.dto.UserDTO;
import com.socialising.dto.response.ResponseDTO;
import com.socialising.service.IUserService;
import com.socialising.service.impl.CustomUserDetailsService;
import com.socialising.service.impl.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController(value = "userApiOfWeb")
@RequestMapping("/api")
public class UserAPI {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserService userService1;
    
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/authentication")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUserName(),
                        userDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @GetMapping("/user/profile")
    public UserDTO getUserInfo() {

        return userService.getProfile();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(userService.register(userDTO));
    }
    @PostMapping("/authenticate")
    public boolean authorize(@Valid @RequestBody UserDTO pass) {
       return userService1.authorize(pass);
    }
    
    @PutMapping("/checkpass/{id}")
    public ResponseDTO checkpass(@PathVariable("id") long id,@RequestBody UserDTO change) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(userService1.changepass(id,change));
        return result;
    }

    @GetMapping("/user/update")
    public ResponseDTO updateInfoUser(@RequestParam(value = "address",required = false) String address,
                                      @RequestParam(value = "fullName",required = false) String fullName,
                                      @RequestParam(value = "phone",required = false) String phone,
                                      @RequestParam(value = "email",required = false) String email){
        ResponseDTO responseDTO = new ResponseDTO();
        UserDTO userDTO = userService.normalUserUpdate(fullName,email,phone,address);
        if(userDTO == null){
            responseDTO.setMessage("faild");
            responseDTO.setData(null);
        }
        else {
            responseDTO.setMessage("success");
            responseDTO.setData(userDTO);
        }
        return responseDTO;
    }

}
