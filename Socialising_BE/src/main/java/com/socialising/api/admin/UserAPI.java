package com.socialising.api.admin;

import com.socialising.dto.UserDTO;
import com.socialising.dto.request.RequestDTO;
import com.socialising.dto.response.ResponseDTO;
import com.socialising.service.IUserService;
import com.socialising.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController(value = "userApiOfAdmin")
@RequestMapping("/api/admin/user")
public class UserAPI {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private UserService userService1;

    @GetMapping
    public ResponseDTO showUser() {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(userService.findAll());
        return result;
    }

    @GetMapping("/{id}")
    public ResponseDTO showUserDetail(@PathVariable("id") long id) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(userService.findById(id));
        return result;
    }

    @PostMapping
    public ResponseDTO createUser(@RequestBody UserDTO newUser) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(userService.create(newUser));
        return result;
    }

    @PutMapping("/{id}")
    public ResponseDTO updateUser(@PathVariable("id") long id, @RequestBody UserDTO updateUser) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(userService.update(id, updateUser));
        return result;
    }

    @DeleteMapping
    public ResponseDTO deleteUser(@RequestBody RequestDTO requestDTO) {
        ResponseDTO result = new ResponseDTO();
        userService.delete(requestDTO.getIds());
        result.setMessage("success");
        return result;
    }
    @PutMapping("/checkpass/{id}")
    public ResponseDTO checkpass(@PathVariable("id") long id,@RequestBody UserDTO change) {
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(userService1.changepass(id,change));
        return result;
    }

}
