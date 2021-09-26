package com.spring.socialising.controllers;

import com.spring.socialising.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    @GetMapping("/information")
    public User getInformationUser(){
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }
}
