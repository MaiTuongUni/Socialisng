package com.spring.socialising.controllers;

import com.spring.socialising.services.taikhoan.TaiKhoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping("/rest/tai-khoan")
public class AccountController {
    private final TaiKhoanService taiKhoanService;

    public AccountController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getRoleTaiKhoans() {
        return new ResponseEntity<>(taiKhoanService.getRoleTaiKhoans(), HttpStatus.OK);
    }
}
