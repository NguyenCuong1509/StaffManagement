package com.cuongmn.StaffManegement.controller;

import com.cuongmn.StaffManegement.dto.request.LoginRequest;
import com.cuongmn.StaffManegement.dto.response.ApiResponse;
import com.cuongmn.StaffManegement.dto.response.LoginResponse;
import com.cuongmn.StaffManegement.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ApiResponse.<LoginResponse>builder()
                .message("Login successfully")
                .result(authService.login(request))
                .build();
    }
}