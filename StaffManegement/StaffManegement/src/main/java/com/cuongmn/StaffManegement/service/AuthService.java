package com.cuongmn.StaffManegement.service;


import com.cuongmn.StaffManegement.dto.request.LoginRequest;
import com.cuongmn.StaffManegement.dto.response.LoginResponse;
import com.cuongmn.StaffManegement.entity.Staff;
import com.cuongmn.StaffManegement.exception.AppException;
import com.cuongmn.StaffManegement.exception.ErrorCode;
import com.cuongmn.StaffManegement.repository.StaffRepository;
import com.cuongmn.StaffManegement.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        Staff staff = staffRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_CREDENTIALS));

        boolean authenticated = passwordEncoder.matches(
                request.getPassword(),
                staff.getPassword()
        );

        if (!authenticated) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        UserDetails userDetails = User.builder()
                .username(staff.getUsername())
                .password(staff.getPassword())
                .roles(staff.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .build();
    }
}